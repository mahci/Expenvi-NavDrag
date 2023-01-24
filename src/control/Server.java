package control;

import configs.Consts.*;
import models.Memo;
import tools.Out;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final String TAG = this.getClass().getSimpleName();

    private static Server mInstance; // Singelton

    // --------------------------------------------------
    private final int PORT = 8000; // always the same
    private final int CONNECTION_TIMEOUT = 60 * 1000; // 1 min
    // --------------------------------------------------

    private ServerSocket mServerSocket;
    private Socket mSocket;
    private PrintWriter mOutPW;
    private BufferedReader mInBR;

    private String mPcDateId;

    private ExecutorService mExecuter;

    //---- Runnable for waiting for incoming connections
    private class ConnectionRunnable implements Runnable {
        private final String TAG = this.getClass().getSimpleName();

        @Override
        public void run() {
            try {
                Out.d(TAG, "Waiting for connections...");
                mServerSocket = new ServerSocket(PORT);
                mSocket = mServerSocket.accept();

                // When reached here, Moose is connected
                Out.d(TAG, "Moose connected!");

                // Create streams
                mInBR = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
                mOutPW = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(mSocket.getOutputStream())), true);

                // Start receiving
                mExecuter.execute(new InRunnable());

                // Set the active technique/pId
//                send(new Memo(
//                        STRINGS.CONFIG,
//                        STRINGS.TECH,
//                        MainFrame.get().mActiveTechnique));

                // Send the exp_id
//                if (MainFrame.get().mMode.equals(Experiment.MODE.TEST)) {
//                    send(new Memo(
//                            STRINGS.LOG,
//                            STRINGS.EXP_ID,
//                            Logger.get().getLogId()));
//                } else if (MainFrame.get().mMode.equals(Experiment.MODE.PRACTICE)) {
//                    send(new Memo(
//                            STRINGS.LOG,
//                            STRINGS.EXP_ID,
//                            Logger.get().getPracticeLogId()));
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //-- Runnable for sending messages to Moose
    private class OutRunnable implements Runnable {
        private final String TAG = this.getClass().getSimpleName();
        Memo message;

        public OutRunnable(Memo mssg) {
            message = mssg;
        }

        @Override
        public void run() {
            if (message != null && mOutPW != null) {
                mOutPW.println(message);
                mOutPW.flush();
            }
        }
    }

    //-- Runnable for receiving messages from Moose
    private class InRunnable implements Runnable {
        private final String TAG = this.getClass().getSimpleName();

        @Override
        public void run() {
            while (!Thread.interrupted() && mInBR != null) {
                try {
//                    Out.d(TAG, "Reading messages...");
                    String message = mInBR.readLine();
                    Out.d(TAG, "Message: " + message);
                    if (message != null) {
                        Memo memo = Memo.valueOf(message);
                        Out.d(TAG, memo.toString());
                        // On Moose connection, send the active technique
                        switch (memo.getAction()) {
                            case STRINGS.CONNECTION -> {
                                if (memo.getMode().equals(STRINGS.INTRO)) {
                                    Out.d(TAG, "INTRO received");
//                                send(new Memo(
//                                        STRINGS.CONFIG,
//                                        STRINGS.TECH,
//                                        MainFrame.get().mActiveTechnique));
                                }
                            }

                            case STRINGS.DRAG -> {

                                switch (memo.getMode()) {
                                    case STRINGS.GRAB -> {
                                        ExperimentFrame.get().grab();
                                    }
                                    case STRINGS.RELEASE -> {
                                        ExperimentFrame.get().release();
                                    }
                                }
                            }

                            case STRINGS.NAV -> {

                                switch (memo.getMode()) {
                                    case STRINGS.UP -> {
                                        ExperimentFrame.get().navUp();
                                    }
                                    case STRINGS.DOWN -> {
                                        ExperimentFrame.get().navDown();
                                    }
                                }
                            }

                        }

                    } else {
                        Out.d(TAG, "Moose disconnected.");
                        start();
                        return;
                    }
                } catch (IOException e) {
                    System.out.println("Error in reading from Moose");
                    start();
                }
            }
        }
    }

    //----------------------------------------------------------------------------------------

    public static Server get() {
        if (mInstance == null) mInstance = new Server();
        return mInstance;
    }

    /**
     * Start the server
     */
    public void start() {
        mExecuter = Executors.newCachedThreadPool();
        mExecuter.execute(new ConnectionRunnable());
    }

    /**
     * Send a Memo to the Moose
     * @param mssg Memo message
     */
    public void send(Memo mssg) {
        if (mExecuter != null) mExecuter.execute(new OutRunnable(mssg));
    }

    public void close() {
        try {
            // Send end message to the Moose
            send(new Memo(STRINGS.CONNECTION, STRINGS.END, ""));

            // Close the socket, etc.
            if (mServerSocket != null && mSocket != null) {
                mServerSocket.close();
                mSocket.close();
            }
            Out.d(TAG, "Shutting down the executer...");
            if (mExecuter != null) mExecuter.shutdownNow();
        } catch (IOException e) {
            Out.e(TAG, "Couldn't close the socket!");
            e.printStackTrace();
        }
    }

}
