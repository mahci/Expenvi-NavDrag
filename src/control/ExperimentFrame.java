package control;

import configs.Consts.*;
import models.Experiment;
import models.Trial;
import views.IntroPanel;
import views.TrialPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExperimentFrame extends JFrame {
    private final String TAG = this.getClass().getSimpleName();

    public static ExperimentFrame self;

    // Constants
    public final static int DPI = DISP.APPLE_DISP_PPI;

    // Screen props
    private Rectangle mScreenBound;
    private int mScreenW, mScreenH;
    private int mFrameW, mFrameH;

    // Views
    private TrialPanel mTrialPanel;

    // Experiment
    private Experiment mExperiment;
    private Trial mTrial;
    private int mTrialInd = 0;

    // ----------------------------------------
    private final int PARTICIPANT = 200;
    public final int TECHNIQUE = 0;
    // ----------------------------------------

    // Actions --------------------------------------------------------------
    public final AbstractAction startTrialsAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mTrialInd = 0;
            mTrial = new Trial();
            showTrialPanel();
        }
    };

    public final AbstractAction endTaskAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    //----------------------------------------------------------------------

    private ExperimentFrame() {
        setDisplayConfig();
        setBackground(Color.WHITE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Server.get().close();
            }
        });
    }

    public static ExperimentFrame get() {
        if (self == null) self = new ExperimentFrame();
        return self;
    }

    public void startExperiment() {
        mExperiment = new Experiment();

        add(new IntroPanel());
        setVisible(true);
    }


    public boolean checkSuccess() {
//        if (mTrialPanel != null) {
//            int selectedInd = mTrialPanel.getMainLetterInd();
////            Out.d(TAG, selectedInd, mTrial.targetInd);
//            return selectedInd == mTrial.targetInd;
//        } else {
//            return false;
//        }

        return false;
    }

    public void nextTrial() {

        getContentPane().removeAll();
        mTrialPanel = new TrialPanel(new Trial());
        getContentPane().add(mTrialPanel);
        mTrialPanel.requestFocusInWindow();

        revalidate();

        // Next trial (if there are any)
//        if (mTrialInd < mExperiment.getNTrials() - 1) {
//            mTrial = mExperiment.getTrial(++mTrialInd);
//            mGenLog.trial_num = mTrialInd + 1;
//            mInstantLog = new InstantLog();
//            showTrialPanel();
//        } else { // Finish experiment
//            Out.d(TAG, "Trials finished!");
//            getContentPane().removeAll();
//            getContentPane().add(new EndPanel());
//            revalidate();
//            repaint();
//
//            mLogger.closeLogs();
//        }
    }

    public void showTrialPanel() {
        getContentPane().removeAll();

        // Add the TrialPanel
        mTrialPanel = new TrialPanel(mTrial);
        getContentPane().add(mTrialPanel);
        mTrialPanel.requestFocusInWindow();

        revalidate();
    }

    /**
     * Set the config for showing panels
     */
    private void setDisplayConfig() {
        setExtendedState(JFrame.MAXIMIZED_BOTH); // maximized frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close on exit

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();

        mScreenBound = gd[1].getDefaultConfiguration().getBounds();
        mScreenW = mScreenBound.width;
        mScreenH = mScreenBound.height;

        mFrameW = getSize().width;
        mFrameH = getSize().height;

        setLocation(
                ((mScreenW / 2) - (mFrameW / 2)) + mScreenBound.x,
                ((mScreenH / 2) - (mFrameH / 2)) + mScreenBound.y
        );
    }

    // Actions ----------------------------------------------------------------------
    public void grab() {
        if (mTrialPanel != null) mTrialPanel.grabObject();
    }

    public void release() {
        if (mTrialPanel != null) mTrialPanel.release();
    }

    public void navUp() {
        if (mTrialPanel != null) mTrialPanel.navUp();
    }

    public void navDown() {
        if (mTrialPanel != null) mTrialPanel.navDown();
    }

}
