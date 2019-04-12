package gui;

import automaton.Automaton;
import cell.coordinates.CellCoordinates;
import cell.states.BinaryState;
import cell.states.CellState;
import cell.states.UniformStateFactory;
import games.GameOfLife;
import games.GameOfLifeRules;
import games.Rule256;
import neighbours.CellNeighborhood;
import neighbours.MooreNeighborhood;
import neighbours.Neighborhood1D;
import neighbours.VonNeumanNeighborhood;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class GraphicUserInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JPanel gameRules;
	private JPanel neighbors;
	private JPanel size2d;
	private JPanel gameRule1D;
	private Board board;
	private JButton acceptSettings;
	private JButton newStateButton;
	private JComboBox<String> typeOfNeighbors;
	private JSpinner radius;
	private JCheckBox mapWrapping;
	private JSlider width;
	private JSlider height;
	private JSpinner ruleNumber;
	private Presenter presenter;
	
	private int rad = 1, gameRule1d = 30;
	public int width2d = 7, height2d = 7;
	private boolean mapWrap = false;
	private String gameRule = "2,3/3", neighbor = "MooreNeighborhood";	
	private Structures struct = Structures.NONE;
	private JButton btnAddGun_1;
	private JButton btnAddBlock_1;
	private JButton btnAddKite_1;
	private JButton btnAddBoat_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicUserInterface frame = new GraphicUserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GraphicUserInterface() {
		initComponents();
		createEvents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel selectMenu = new JPanel();
		board = new Board(width2d, height2d, this, struct);
		
		newStateButton = new JButton("Nowy stan");
		JPanel addStructures = new JPanel();
		addStructures.setBorder(new TitledBorder(null, "Dodaj strukture", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(selectMenu, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(addStructures, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
							.addComponent(newStateButton, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addComponent(board, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(board, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(selectMenu, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 430, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(newStateButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(addStructures, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		addStructures.setLayout(null);
		
		btnAddBoat_1 = new JButton("\u0141\u00F3d\u017A");
		btnAddBoat_1.setBounds(10, 11, 89, 23);
		addStructures.add(btnAddBoat_1);
		
		btnAddKite_1 = new JButton("Latawiec");
		btnAddKite_1.setBounds(109, 11, 89, 23);
		addStructures.add(btnAddKite_1);
		
		btnAddBlock_1 = new JButton("Klocek");
		btnAddBlock_1.setBounds(208, 11, 89, 23);
		addStructures.add(btnAddBlock_1);
		
		btnAddGun_1 = new JButton("Dzia\u0142o");
		btnAddGun_1.setBounds(307, 11, 89, 23);
		addStructures.add(btnAddGun_1);
		
		JPanel gameType = new JPanel();
		gameType.setBorder(new TitledBorder(null, "Gra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		neighbors = new JPanel();
		neighbors.setBorder(new TitledBorder(null, "S\u0105siedztwo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		size2d = new JPanel();
		size2d.setBorder(new TitledBorder(null, "Wymiary planszy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		gameRules = new JPanel();
		gameRules.setBorder(new TitledBorder(null, "Zasady Gry", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		acceptSettings = new JButton("Akceptuj");
		
		gameRule1D = new JPanel();
		gameRule1D.setVisible(false);
		gameRule1D.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Zasady Gry", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_selectMenu = new GroupLayout(selectMenu);
		gl_selectMenu.setHorizontalGroup(
			gl_selectMenu.createParallelGroup(Alignment.TRAILING)
				.addComponent(gameType, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
				.addComponent(gameRules, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
				.addComponent(neighbors, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
				.addComponent(size2d, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_selectMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(acceptSettings, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(gameRule1D, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
		);
		gl_selectMenu.setVerticalGroup(
			gl_selectMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_selectMenu.createSequentialGroup()
					.addComponent(gameType, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(size2d, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(neighbors, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gameRules, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gameRule1D, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(acceptSettings)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		gameRule1D.setLayout(null);
		
		ruleNumber = new JSpinner();
		ruleNumber.setModel(new SpinnerNumberModel(30, 0, 255, 1));
		ruleNumber.setBounds(10, 27, 125, 20);
		gameRule1D.add(ruleNumber);
		gameRules.setLayout(null);
	
		
		textField = new JTextField();
		textField.setText("2,3/3");
		textField.setBounds(10, 21, 127, 20);
		gameRules.add(textField);
		textField.setColumns(10);
		neighbors.setLayout(null);
		
		
		typeOfNeighbors = new JComboBox<>();
		typeOfNeighbors.setModel(new DefaultComboBoxModel<>(new String[] {"Moore Neighborhood", "Von Neuman Neighborhood"}));
		typeOfNeighbors.setBounds(10, 18, 127, 20);
		neighbors.add(typeOfNeighbors);

		
		radius = new JSpinner();
		radius.setToolTipText("");
		radius.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		radius.setBounds(69, 43, 68, 20);
		neighbors.add(radius);

		
		mapWrapping = new JCheckBox("Zawijanie mapy");
		mapWrapping.setHorizontalAlignment(SwingConstants.CENTER);
		mapWrapping.setBounds(10, 67, 127, 23);
		neighbors.add(mapWrapping);

		
		JLabel lblPromie = new JLabel("Promie\u0144:");
		lblPromie.setHorizontalAlignment(SwingConstants.CENTER);
		lblPromie.setBounds(10, 43, 49, 20);
		neighbors.add(lblPromie);
		size2d.setLayout(null);
		
		width = new JSlider();
		width.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		width.setPaintLabels(true);
		width.setSnapToTicks(true);
		width.setMinimum(20);
		width.setMaximum(200);
		width.setBounds(10, 37, 127, 20);
		size2d.add(width);
		
		
		height = new JSlider();
		height.setMaximum(200);
		height.setMinimum(20);
		height.setBounds(10, 78, 127, 20);
		size2d.add(height);

		
		JLabel lblSzeroko = new JLabel("szeroko\u015B\u0107:");
		lblSzeroko.setBounds(10, 23, 66, 14);
		size2d.add(lblSzeroko);
		
		JLabel lblWysoko = new JLabel("wysoko\u015B\u0107:");
		lblWysoko.setBounds(10, 63, 66, 14);
		size2d.add(lblWysoko);
		gameType.setLayout(null);
		
		comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("Game of Life")){
					gameRules.setVisible(true);
					neighbors.setVisible(true);
					size2d.setVisible(true);
					gameRule1D.setVisible(false);
				}
				if(comboBox.getSelectedItem().equals("1Dim Automaton")){
					gameRules.setVisible(false);
					neighbors.setVisible(false);
					size2d.setVisible(true);
					gameRule1D.setVisible(true);
				}
				
			}
		});
		
		comboBox.setBounds(10, 20, 127, 20);
		gameType.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Game of Life", "1Dim Automaton"}));
		selectMenu.setLayout(gl_selectMenu);
		contentPane.setLayout(gl_contentPane);

        List<Integer> aliveRules = new ArrayList<Integer>();
        List<Integer> deadRules = new ArrayList<Integer>();
        aliveRules.add(2);
        aliveRules.add(3);
        deadRules.add(3);
 
        Automaton automaton = new GameOfLife(
        		new UniformStateFactory(BinaryState.DEAD),
                new MooreNeighborhood(1, false, width2d, height2d),
                width2d,
                height2d, aliveRules, deadRules
        );
		presenter = new Presenter(this,automaton);
	}
	
	private void createEvents(){
		newStateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				presenter.nextClicked();
			}
		});
		board.addMouseListener(board.createMouseListner(presenter));
		
		acceptSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				readData();

				if(comboBox.getSelectedItem().equals("Game of Life")){
					GameOfLife newGOF = setGameOfLife();
					presenter.changeAutomaton(newGOF);
					
				}
				if(comboBox.getSelectedItem().equals("1Dim Automaton")){
					Rule256 newR256 = set1DimAutomaton();
					presenter.changeAutomaton(newR256);
				}	
			}
		});
		
		btnAddBoat_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				struct = Structures.BOAT;
			}
		});

		btnAddKite_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				struct = Structures.KITE;
			}
		});
		btnAddBlock_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				struct = Structures.BLOCK;
			}
		});
		btnAddGun_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				struct = Structures.GUN;
			}
		});
	}

	private void readData(){
		gameRule1d = (int) ruleNumber.getValue();
		gameRule = textField.getText();
		width2d = width.getValue();
		height2d = height.getValue();
		if(typeOfNeighbors.getSelectedItem().equals("Moore Neighborhood")){
			neighbor = "Moore Neighborhood";
		}
		if(typeOfNeighbors.getSelectedItem().equals("Von Neuman Neighborhood")){
			neighbor = "Von Neuman Neighborhood";
		}
		rad = (int) radius.getValue();
		mapWrap = mapWrapping.isSelected();
	}
	
	public Map<CellCoordinates, CellState> supportOneDim(){
		board.oldMap();
		return board.oldMapState;
	}
	
	public boolean isOneDimention(){
		return comboBox.getSelectedItem().equals("1Dim Automaton");
	}

	public int getMapWidth(){
		return this.width2d;
	}

	public int getMapHeight(){
		return this.height2d;
	}

	public Structures getStructure(){
		return this.struct;
	}

	public void resetStructure(){
		struct = Structures.NONE;
	}
	
	public void resizeBoardSizeInCellsTo(int xCells,int yCells) {
		board.resizeBoardSizeInCellsTo(xCells, yCells);
	}

	public void changeCell(int x, int y, BinaryState state) {
		board.setCellTo(x, y, state);
		board.repaint();
	}

	public GameOfLife setGameOfLife(){
		CellNeighborhood neighborhood = null;
		GameOfLifeRules rules = new GameOfLifeRules();
		List<Integer> aliveRules = new ArrayList<Integer>();
		List<Integer> deadRules = new ArrayList<Integer>();
		
		String[] partRules = gameRule.split("/");
		aliveRules = rules.convertStringToListAlive(partRules[0]);
		deadRules = rules.convertStringToListDead(partRules[1]);
		
		if(neighbor.equals("Moore Neighborhood")){
			neighborhood = new MooreNeighborhood(rad, mapWrap, width2d, height2d);
		}
		else if(neighbor.equals("Von Neuman Neighborhood")){
			neighborhood = new VonNeumanNeighborhood(rad, mapWrap, width2d, height2d);
		}
		else throw new RuntimeException("zrobi³em literowke dlatego mi nie weszlo w poprzedine ify");

		return new GameOfLife(
				new UniformStateFactory(BinaryState.DEAD),
				neighborhood,
				width2d,
				height2d,
				aliveRules,
				deadRules
		);
	}
	
	public Rule256 set1DimAutomaton(){
		return new Rule256(
        		new UniformStateFactory(BinaryState.DEAD),
                new Neighborhood1D(width2d),
                width2d,
                gameRule1d
            );
	}
}
