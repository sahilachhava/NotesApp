import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    JLabel mainLabel;
    JTable notesTable;
    JScrollPane scrollPane;
    Font titleFont, btnFont;
    JButton mainBtn;
    String[] columnNames = {"ID", "Notes"};
    String[][] allNotes;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "ADD"){
                dispose();
                new NoteEditor(Util.notesIndex, e.getActionCommand());
            }else if(e.getActionCommand() == "UPDATE"){
                int selectedRow = notesTable.getSelectedRow();
                if(!notesTable.isRowSelected(selectedRow)){
                    JOptionPane.showMessageDialog(null, "Please Select Note to Update");
                }else{
                    String noteID = (String) notesTable.getValueAt(selectedRow, 0);
                    dispose();
                    new NoteEditor(Integer.parseInt(noteID)-1, e.getActionCommand());
                }
            }else if(e.getActionCommand() == "DELETE"){
                int selectedRow = notesTable.getSelectedRow();
                if(!notesTable.isRowSelected(selectedRow)){
                    JOptionPane.showMessageDialog(null, "Please Select Note to Delete");
                }else{
                    int noteID = Integer.parseInt((String) notesTable.getValueAt(selectedRow, 0));
                    Util.allNotes.remove(noteID-1);
                    Util.notesIndex--;
                    JOptionPane.showMessageDialog(null, "Selected Note Deleted");
                    dispose();
                    new MainFrame();
                }
            }
        }
    };

    MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                int selectedRow = notesTable.getSelectedRow();
                int noteID = Integer.parseInt((String) notesTable.getValueAt(selectedRow, 0));
                dispose();
                new NoteEditor(noteID-1, "VIEW");
            }
        }
    };

    public MainFrame(){
        super("Notes");

        titleFont = new Font("Times New Roman", Font.BOLD, 26);
        btnFont = new Font("Times New Roman", Font.BOLD, 22);

        allNotes = new String[Util.allNotes.size()][2];
        int index = 0;
        for(Notes note : Util.allNotes){
            allNotes[index] = new String[]{
                    String.valueOf(note.noteID+1),
                    note.noteTitle
            };
            index++;
        }

        mainLabel = new JLabel("Notes App");
        mainLabel.setFont(titleFont);
        mainLabel.setBounds(150, 10, 120, 30);
        add(mainLabel);

        mainLabel = new JLabel();
        mainLabel.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.BLACK));
        mainLabel.setBounds(0, 45, 420, 5);
        add(mainLabel);

        TableModel tabModel = new DefaultTableModel(allNotes, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        notesTable = new JTable(tabModel);
        notesTable.setTableHeader(null);
        notesTable.setRowHeight(40);
        notesTable.setShowGrid(false);
        notesTable.getColumnModel().getColumn(0).setMaxWidth(50);
        notesTable.setShowHorizontalLines(true);
        notesTable.setShowVerticalLines(false);
        notesTable.setIntercellSpacing(new Dimension(5, 5));
        notesTable.setGridColor(Color.BLACK);
        notesTable.setFont(titleFont);
        notesTable.addMouseListener(mouseListener);

        scrollPane = new JScrollPane(notesTable);
        scrollPane.setBounds(0, 50, 420, 377);
        add(scrollPane);

        mainLabel = new JLabel();
        mainLabel.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.BLACK));
        mainLabel.setBounds(0, 425, 420, 5);
        add(mainLabel);

        mainBtn = new JButton("ADD");
        mainBtn.setFont(btnFont);
        mainBtn.setBounds(10, 440, 125, 50);
        mainBtn.addActionListener(actionListener);
        add(mainBtn);

        mainBtn = new JButton("UPDATE");
        mainBtn.setFont(btnFont);
        mainBtn.setBounds(150, 440, 125, 50);
        mainBtn.addActionListener(actionListener);
        add(mainBtn);

        mainBtn = new JButton("DELETE");
        mainBtn.setFont(btnFont);
        mainBtn.setBounds(280, 440, 125, 50);
        mainBtn.addActionListener(actionListener);
        add(mainBtn);

        setLayout(null);
        setSize(420,525);
        setLocation(500, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
