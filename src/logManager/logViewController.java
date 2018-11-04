package logManager;

import application.applicationController;
import application.helperMethod;
import application.webRequestHandler;
import constants.enumeration;
import constants.enumeration.logType;
import constants.preferences;
import constants.status;
import constants.string;
import crawler.crawler;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;

public class logViewController extends javax.swing.JFrame
{

    /*Variable Declaration*/
    public crawler crawlerObject;
    public logEvents eventManager = new logEvents();

    /*Initialization*/
    public logViewController() throws ClassNotFoundException, InstantiationException, InstantiationException, IllegalAccessException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        initComponents();
        initialization();
    }

    private void initialization()
    {
        initializeStyle();
        initializeDefaultText();
        eventManager.Initialize(this);
    }

    public void initializeStyle()
    {
        jServerErrorPane.setContentType("text/html");
        jSystemProgressPane.setContentType("text/html");
        jUrlFoundPane.setContentType("text/html");
        jWarningPane.setContentType("text/html");
        jPauseBtn.setBackground(Color.green);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height - 24);
    }

    public void initializeTheme()
    {
        try
        {
            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        }
        catch (ParseException | UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
    }

    public void initializeDefaultText()
    {
        jThread.setText("  " + preferences.maxThreadCount);
        jDefaultLink.setText("  " + string.baseLink);
        jCurrentNetwork.setText("  " + preferences.networkType);
        jStatus.setText("  " + status.appStatus);
        jMaxThread.setText("  " + preferences.maxUrlDepth);

        jThread.setToolTipText("Max Thread Count : " + preferences.maxThreadCount);
        jDefaultLink.setToolTipText("Base Link : " + string.baseLink);
        jCurrentNetwork.setToolTipText("Network Type : " + preferences.networkType);
        jStatus.setToolTipText("App Status : " + status.appStatus);
        jMaxThread.setToolTipText("Max Url Depth : " + preferences.maxUrlDepth);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jStartBtn = new javax.swing.JButton();
        jPauseBtn = new javax.swing.JButton();
        jBackupBtn = new javax.swing.JButton();
        jPreferenceBtn = new javax.swing.JButton();
        jRestartBtn = new javax.swing.JButton();
        jClearBtn = new javax.swing.JButton();
        jUpdateLogBtn = new javax.swing.JButton();
        jMenuBarLbl = new javax.swing.JLabel();
        jSystemProgressLbl = new javax.swing.JLabel();
        jServerErrorLbl = new javax.swing.JLabel();
        jUrlFoundLbl = new javax.swing.JLabel();
        jMenuSeperator = new javax.swing.JSeparator();
        jPropertiesPane = new javax.swing.JPanel();
        jPropertiesLbl = new javax.swing.JLabel();
        jThreadCountLbl = new javax.swing.JLabel();
        jPauseThreadLbl = new javax.swing.JLabel();
        jCurrentNetworkLbl = new javax.swing.JLabel();
        jRunningThreadLbl = new javax.swing.JLabel();
        jStatusLbl = new javax.swing.JLabel();
        jThread = new javax.swing.JLabel();
        pausedThread = new javax.swing.JLabel();
        jCurrentNetwork = new javax.swing.JLabel();
        runningThread = new javax.swing.JLabel();
        jStatus = new javax.swing.JLabel();
        jMaxUrlThreadLbl = new javax.swing.JLabel();
        jMaxThread = new javax.swing.JLabel();
        jDefaultLinkLbl = new javax.swing.JLabel();
        jDefaultLink = new javax.swing.JLabel();
        jServerErrorPaneScroller = new javax.swing.JScrollPane();
        jServerErrorPane = new javax.swing.JTextPane();
        jSystemProgressPaneScroller = new javax.swing.JScrollPane();
        jSystemProgressPane = new javax.swing.JTextPane();
        jUrlFoundPaneScroller = new javax.swing.JScrollPane();
        jUrlFoundPane = new javax.swing.JTextPane();
        jWarningPaneScroller = new javax.swing.JScrollPane();
        jWarningPane = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable3);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(249, 248, 248));
        setMinimumSize(new java.awt.Dimension(156, 110));
        setResizable(false);

        jStartBtn.setText("Start Crawling");
        jStartBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jStartBtnActionPerformed(evt);
            }
        });

        jPauseBtn.setText("Pause Crawling");
        jPauseBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jPauseBtnActionPerformed(evt);
            }
        });

        jBackupBtn.setText("Create Backup");
        jBackupBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBackupBtnActionPerformed(evt);
            }
        });

        jPreferenceBtn.setText("Preferences");
        jPreferenceBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jPreferenceBtnActionPerformed(evt);
            }
        });

        jRestartBtn.setText("Restart");
        jRestartBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRestartBtnActionPerformed(evt);
            }
        });

        jClearBtn.setText("Clear Logs");
        jClearBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jClearBtnActionPerformed(evt);
            }
        });

        jUpdateLogBtn.setText("Update Logs");
        jUpdateLogBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jUpdateLogBtnActionPerformed(evt);
            }
        });

        jMenuBarLbl.setBackground(new java.awt.Color(249, 248, 248));
        jMenuBarLbl.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jMenuBarLbl.setForeground(new java.awt.Color(51, 51, 51));
        jMenuBarLbl.setText("  Menu Bar");
        jMenuBarLbl.setOpaque(true);

        jSystemProgressLbl.setBackground(new java.awt.Color(249, 248, 248));
        jSystemProgressLbl.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jSystemProgressLbl.setForeground(new java.awt.Color(51, 51, 51));
        jSystemProgressLbl.setText("System Progress");
        jSystemProgressLbl.setOpaque(true);

        jServerErrorLbl.setBackground(new java.awt.Color(249, 248, 248));
        jServerErrorLbl.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jServerErrorLbl.setForeground(new java.awt.Color(51, 51, 51));
        jServerErrorLbl.setText("Server Error");
        jServerErrorLbl.setOpaque(true);

        jUrlFoundLbl.setBackground(new java.awt.Color(249, 248, 248));
        jUrlFoundLbl.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jUrlFoundLbl.setForeground(new java.awt.Color(51, 51, 51));
        jUrlFoundLbl.setText("URL Found");
        jUrlFoundLbl.setOpaque(true);

        jPropertiesPane.setBackground(new java.awt.Color(255, 255, 255));
        jPropertiesPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(243, 243, 243)));
        jPropertiesPane.setMaximumSize(new java.awt.Dimension(453, 156));
        jPropertiesPane.setMinimumSize(new java.awt.Dimension(453, 156));
        jPropertiesPane.setPreferredSize(new java.awt.Dimension(453, 156));

        jPropertiesLbl.setBackground(new java.awt.Color(0, 102, 153));
        jPropertiesLbl.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jPropertiesLbl.setForeground(new java.awt.Color(51, 51, 51));
        jPropertiesLbl.setText("  Properties");

        jThreadCountLbl.setBackground(javax.swing.UIManager.getDefaults().getColor("FormattedTextField.selectionBackground"));
        jThreadCountLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jThreadCountLbl.setForeground(new java.awt.Color(255, 255, 255));
        jThreadCountLbl.setText("  Total Threads   ");
        jThreadCountLbl.setOpaque(true);
        jThreadCountLbl.setPreferredSize(new java.awt.Dimension(0, 22));

        jPauseThreadLbl.setBackground(javax.swing.UIManager.getDefaults().getColor("FormattedTextField.selectionBackground"));
        jPauseThreadLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPauseThreadLbl.setForeground(new java.awt.Color(255, 255, 255));
        jPauseThreadLbl.setText("  Paused Thread   ");
        jPauseThreadLbl.setOpaque(true);
        jPauseThreadLbl.setPreferredSize(new java.awt.Dimension(0, 22));

        jCurrentNetworkLbl.setBackground(javax.swing.UIManager.getDefaults().getColor("FormattedTextField.selectionBackground"));
        jCurrentNetworkLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCurrentNetworkLbl.setForeground(new java.awt.Color(255, 255, 255));
        jCurrentNetworkLbl.setText("  Current Network   ");
        jCurrentNetworkLbl.setOpaque(true);
        jCurrentNetworkLbl.setPreferredSize(new java.awt.Dimension(0, 22));

        jRunningThreadLbl.setBackground(javax.swing.UIManager.getDefaults().getColor("FormattedTextField.selectionBackground"));
        jRunningThreadLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRunningThreadLbl.setForeground(new java.awt.Color(255, 255, 255));
        jRunningThreadLbl.setText("  Running Thread");
        jRunningThreadLbl.setOpaque(true);
        jRunningThreadLbl.setPreferredSize(new java.awt.Dimension(0, 22));

        jStatusLbl.setBackground(javax.swing.UIManager.getDefaults().getColor("FormattedTextField.selectionBackground"));
        jStatusLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStatusLbl.setForeground(new java.awt.Color(255, 255, 255));
        jStatusLbl.setText("  Status   ");
        jStatusLbl.setMaximumSize(new java.awt.Dimension(52, 22));
        jStatusLbl.setMinimumSize(new java.awt.Dimension(52, 22));
        jStatusLbl.setOpaque(true);
        jStatusLbl.setPreferredSize(new java.awt.Dimension(52, 22));

        jThread.setBackground(new java.awt.Color(245, 241, 241));
        jThread.setText("  1000");
        jThread.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(222, 222, 222), 1, true));
        jThread.setOpaque(true);
        jThread.setPreferredSize(new java.awt.Dimension(0, 22));

        pausedThread.setBackground(new java.awt.Color(245, 241, 241));
        pausedThread.setText("  0");
        pausedThread.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(222, 222, 222), 1, true));
        pausedThread.setMaximumSize(new java.awt.Dimension(0, 22));
        pausedThread.setMinimumSize(new java.awt.Dimension(0, 22));
        pausedThread.setOpaque(true);
        pausedThread.setPreferredSize(new java.awt.Dimension(0, 22));

        jCurrentNetwork.setBackground(new java.awt.Color(245, 241, 241));
        jCurrentNetwork.setText("  onion");
        jCurrentNetwork.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(222, 222, 222), 1, true));
        jCurrentNetwork.setOpaque(true);
        jCurrentNetwork.setPreferredSize(new java.awt.Dimension(0, 22));

        runningThread.setBackground(new java.awt.Color(245, 241, 241));
        runningThread.setText("  0");
        runningThread.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(222, 222, 222), 1, true));
        runningThread.setOpaque(true);
        runningThread.setPreferredSize(new java.awt.Dimension(0, 22));

        jStatus.setBackground(new java.awt.Color(245, 241, 241));
        jStatus.setText("  running");
        jStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(222, 222, 222), 1, true));
        jStatus.setOpaque(true);
        jStatus.setPreferredSize(new java.awt.Dimension(0, 22));

        jMaxUrlThreadLbl.setBackground(javax.swing.UIManager.getDefaults().getColor("FormattedTextField.selectionBackground"));
        jMaxUrlThreadLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMaxUrlThreadLbl.setForeground(new java.awt.Color(255, 255, 255));
        jMaxUrlThreadLbl.setText("  Max URL Thread   ");
        jMaxUrlThreadLbl.setMaximumSize(new java.awt.Dimension(52, 22));
        jMaxUrlThreadLbl.setMinimumSize(new java.awt.Dimension(52, 22));
        jMaxUrlThreadLbl.setOpaque(true);
        jMaxUrlThreadLbl.setPreferredSize(new java.awt.Dimension(52, 22));

        jMaxThread.setBackground(new java.awt.Color(245, 241, 241));
        jMaxThread.setText("  10");
        jMaxThread.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(222, 222, 222), 1, true));
        jMaxThread.setOpaque(true);
        jMaxThread.setPreferredSize(new java.awt.Dimension(0, 22));

        jDefaultLinkLbl.setBackground(javax.swing.UIManager.getDefaults().getColor("FormattedTextField.selectionBackground"));
        jDefaultLinkLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jDefaultLinkLbl.setForeground(new java.awt.Color(255, 255, 255));
        jDefaultLinkLbl.setText("  Default Link   ");
        jDefaultLinkLbl.setOpaque(true);
        jDefaultLinkLbl.setPreferredSize(new java.awt.Dimension(0, 22));

        jDefaultLink.setBackground(new java.awt.Color(245, 241, 241));
        jDefaultLink.setText("  google.com");
        jDefaultLink.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(222, 222, 222), 1, true));
        jDefaultLink.setMaximumSize(new java.awt.Dimension(0, 22));
        jDefaultLink.setMinimumSize(new java.awt.Dimension(0, 22));
        jDefaultLink.setOpaque(true);
        jDefaultLink.setPreferredSize(new java.awt.Dimension(0, 22));

        javax.swing.GroupLayout jPropertiesPaneLayout = new javax.swing.GroupLayout(jPropertiesPane);
        jPropertiesPane.setLayout(jPropertiesPaneLayout);
        jPropertiesPaneLayout.setHorizontalGroup(
            jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPropertiesPaneLayout.createSequentialGroup()
                .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPropertiesLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPropertiesPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jDefaultLinkLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(jCurrentNetworkLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jThreadCountLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPauseThreadLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(2, 2, 2)
                        .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPropertiesPaneLayout.createSequentialGroup()
                                .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jThread, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(pausedThread, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCurrentNetwork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jRunningThreadLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jMaxUrlThreadLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(jStatusLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(2, 2, 2)
                                .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jMaxThread, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(runningThread, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
                            .addComponent(jDefaultLink, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPropertiesPaneLayout.setVerticalGroup(
            jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPropertiesPaneLayout.createSequentialGroup()
                .addComponent(jPropertiesLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jThreadCountLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jThread, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jStatusLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPauseThreadLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(pausedThread, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMaxThread, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMaxUrlThreadLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPropertiesPaneLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(runningThread, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRunningThreadLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCurrentNetworkLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPropertiesPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCurrentNetwork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPropertiesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDefaultLinkLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jDefaultLink, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jServerErrorPane.setAutoscrolls(false);
        jServerErrorPane.setMaximumSize(new java.awt.Dimension(6, 20));
        jServerErrorPaneScroller.setViewportView(jServerErrorPane);

        jSystemProgressPane.setAutoscrolls(false);
        jSystemProgressPane.setMaximumSize(new java.awt.Dimension(6, 20));
        jSystemProgressPaneScroller.setViewportView(jSystemProgressPane);

        jUrlFoundPane.setAutoscrolls(false);
        jUrlFoundPane.setMaximumSize(new java.awt.Dimension(6, 20));
        jUrlFoundPaneScroller.setViewportView(jUrlFoundPane);

        jWarningPane.setMaximumSize(new java.awt.Dimension(6, 6));
        jWarningPaneScroller.setViewportView(jWarningPane);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jMenuBarLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPreferenceBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jClearBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRestartBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBackupBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                .addComponent(jUpdateLogBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jMenuSeperator)
                            .addComponent(jPauseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jStartBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSystemProgressLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPropertiesPane, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSystemProgressPaneScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jServerErrorLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jServerErrorPaneScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jWarningPaneScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jUrlFoundLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jUrlFoundPaneScroller))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jMenuBarLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSystemProgressLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jServerErrorLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jUrlFoundLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jServerErrorPaneScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPreferenceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jBackupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRestartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jUpdateLogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSystemProgressPaneScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jMenuSeperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jStartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))
                            .addComponent(jPropertiesPane, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jWarningPaneScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jUrlFoundPaneScroller)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem1ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jBackupBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBackupBtnActionPerformed
    {//GEN-HEADEREND:event_jBackupBtnActionPerformed
        eventManager.onCreateBackup();
    }//GEN-LAST:event_jBackupBtnActionPerformed

    private void jClearBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jClearBtnActionPerformed
    {//GEN-HEADEREND:event_jClearBtnActionPerformed
        eventManager.onClearLogs();
    }//GEN-LAST:event_jClearBtnActionPerformed

    private void jRestartBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRestartBtnActionPerformed
    {//GEN-HEADEREND:event_jRestartBtnActionPerformed
        eventManager.onRestart();
    }//GEN-LAST:event_jRestartBtnActionPerformed

    private void jPauseBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jPauseBtnActionPerformed
    {//GEN-HEADEREND:event_jPauseBtnActionPerformed
        eventManager.onPauseCrawler();
    }//GEN-LAST:event_jPauseBtnActionPerformed

    private void jStartBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jStartBtnActionPerformed
    {//GEN-HEADEREND:event_jStartBtnActionPerformed
        eventManager.onStartCrawler();
    }//GEN-LAST:event_jStartBtnActionPerformed

    private void jUpdateLogBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jUpdateLogBtnActionPerformed
    {//GEN-HEADEREND:event_jUpdateLogBtnActionPerformed
        eventManager.onUpdateLogs();
    }//GEN-LAST:event_jUpdateLogBtnActionPerformed

    private void jPreferenceBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jPreferenceBtnActionPerformed
    {//GEN-HEADEREND:event_jPreferenceBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPreferenceBtnActionPerformed

    /*Helper Method*/
    public String createMessage(String color, String message, String type)
    {
        String requestMessage = ""
                + "<span style=\"color:" + color + ";font-weight:bold\">" + type + "</span><br>"
                + "<span style=\"color:blue\">" + message + "</span>"
                + "<br><br>";

        return requestMessage;
    }

    public void showMessage(logMessageModel model, logType type) throws IOException, BadLocationException
    {
        String message = "";

        HTMLDocument doc = new HTMLDocument();
        if (null != type)
        {
            switch (type)
            {
                case urlFound:
                    doc = (HTMLDocument) jUrlFoundPane.getStyledDocument();
                    message = createMessage("green", model.getMessageType(),model.getMessage());
                    break;
                case request:
                    doc = (HTMLDocument) jSystemProgressPane.getStyledDocument();
                    message = createMessage("green", model.getMessageType(),model.getMessage());
                    break;
                case warning:
                    doc = (HTMLDocument) jWarningPane.getStyledDocument();
                    message = createMessage("#ffa64d",model.getMessageType(), "Warning" + model.getMessage());
                    break;
                case error:
                    doc = (HTMLDocument) jServerErrorPane.getStyledDocument();
                    message = createMessage("red", model.getMessageType(),"Error" + model.getMessage());
                    break;
                default:
                    break;
            }
        }

        doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), message);
    }

    public void resetCrawler() throws InstantiationException, InstantiationException, ClassNotFoundException, ClassNotFoundException, IllegalAccessException, IllegalAccessException, UnsupportedLookAndFeelException, UnsupportedLookAndFeelException, UnsupportedLookAndFeelException, UnsupportedLookAndFeelException, UnsupportedLookAndFeelException, UnsupportedLookAndFeelException
    {
        try
        {
            status.appStatus = enumeration.appStatus.paused;
            webRequestHandler.getInstance().removeAllRequests();
            applicationController.stopAllThread();
            logModel.getInstance().resetQueues();
            applicationController.main(null);
        }
        catch (InterruptedException | IOException | InstantiationException | ParseException ex)
        {
            Logger.getLogger(logViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }

    public void run() throws ParseException
    {
        java.awt.EventQueue.invokeLater(() ->
        {
            try
            {
                initializeTheme();
                logViewController crawler = new logViewController();
                crawler.crawlerObject = crawlerObject;
                crawler.setVisible(true);
                Point point = helperMethod.centreDimension(crawler.getSize().width,crawler.getSize().height);
                crawler.setLocation(point);
                crawler.getContentPane().setBackground(new Color(249, 248, 248));

            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
            {
                Logger.getLogger(logViewController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jBackupBtn;
    public javax.swing.JButton jClearBtn;
    public javax.swing.JLabel jCurrentNetwork;
    public javax.swing.JLabel jCurrentNetworkLbl;
    public javax.swing.JLabel jDefaultLink;
    public javax.swing.JLabel jDefaultLinkLbl;
    public javax.swing.JLabel jMaxThread;
    public javax.swing.JLabel jMaxUrlThreadLbl;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JLabel jMenuBarLbl;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JSeparator jMenuSeperator;
    public javax.swing.JButton jPauseBtn;
    public javax.swing.JLabel jPauseThreadLbl;
    public javax.swing.JButton jPreferenceBtn;
    public javax.swing.JLabel jPropertiesLbl;
    public javax.swing.JPanel jPropertiesPane;
    public javax.swing.JButton jRestartBtn;
    public javax.swing.JLabel jRunningThreadLbl;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane5;
    public javax.swing.JLabel jServerErrorLbl;
    public javax.swing.JTextPane jServerErrorPane;
    public javax.swing.JScrollPane jServerErrorPaneScroller;
    public javax.swing.JButton jStartBtn;
    public javax.swing.JLabel jStatus;
    public javax.swing.JLabel jStatusLbl;
    public javax.swing.JLabel jSystemProgressLbl;
    public javax.swing.JTextPane jSystemProgressPane;
    public javax.swing.JScrollPane jSystemProgressPaneScroller;
    public javax.swing.JTable jTable3;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JLabel jThread;
    public javax.swing.JLabel jThreadCountLbl;
    public javax.swing.JButton jUpdateLogBtn;
    public javax.swing.JLabel jUrlFoundLbl;
    public javax.swing.JTextPane jUrlFoundPane;
    public javax.swing.JScrollPane jUrlFoundPaneScroller;
    public javax.swing.JTextPane jWarningPane;
    public javax.swing.JScrollPane jWarningPaneScroller;
    public javax.swing.JLabel pausedThread;
    public javax.swing.JLabel runningThread;
    // End of variables declaration//GEN-END:variables
}
