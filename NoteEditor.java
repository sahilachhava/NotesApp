import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NoteEditor extends JFrame {
    JLabel mainLabel;
    Font titleFont, btnFont;
    JTextField txtBox;
    JTextArea txtArea;
    JButton mainBtn;
    String nTitle, nText;
    int noteID;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newTitle = txtBox.getText();
            String newText = txtArea.getText();

            if(e.getActionCommand() == "Add Note"){
                if(newTitle.isEmpty() || newText.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Title or Note");
                }else if(newTitle.equals("Enter Note Title") || newText.equals("Enter your note here")){
                    JOptionPane.showMessageDialog(null, "Please Enter Title and Note");
                }else{
                    Util.allNotes.add(new Notes(noteID, newTitle, newText));
                    Util.notesIndex++;
                    JOptionPane.showMessageDialog(null, "New Note Added Successfully");
                    dispose();
                    new MainFrame();
                }
            }else if(e.getActionCommand() == "Update Note"){
                if(newTitle.isEmpty() || newText.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Title or Note");
                }else{
                    Util.allNotes.get(noteID).setNoteTitle(newTitle);
                    Util.allNotes.get(noteID).setNoteText(newText);
                    JOptionPane.showMessageDialog(null, "Note Updated Successfully");
                    dispose();
                    new MainFrame();
                }
            }else{
                dispose();
                new MainFrame();
            }
        }
    };

    public NoteEditor(int noteID, String editType){
        this.noteID = noteID;
        this.nTitle = "Enter Note Title";
        this.nText = "Enter your note here";

        titleFont = new Font("Times New Roman", Font.BOLD, 26);
        btnFont = new Font("Times New Roman", Font.BOLD, 22);

        if(editType == "UPDATE" || editType == "VIEW"){
            this.nTitle = Util.allNotes.get(noteID).noteTitle;
            this.nText = Util.allNotes.get(noteID).noteText;
        }

        mainLabel = new JLabel(editType + " New Note");
        mainLabel.setFont(titleFont);
        mainLabel.setBounds(120, 10, 300, 30);
        add(mainLabel);

        mainLabel = new JLabel();
        mainLabel.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.BLACK));
        mainLabel.setBounds(0, 45, 420, 5);
        add(mainLabel);

        txtBox = new JTextField(nTitle);
        txtBox.setFont(titleFont);
        if(editType == "VIEW"){
            txtBox.setEnabled(false);
        }
        txtBox.setBounds(10, 60, 400, 50);
        add(txtBox);

        txtArea = new JTextArea(nText);
        txtArea.setFont(titleFont);
        if(editType == "VIEW"){
            txtArea.setEnabled(false);
        }
        txtArea.setBounds(10, 120, 400, 300);
        add(txtArea);

        mainLabel = new JLabel();
        mainLabel.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.BLACK));
        mainLabel.setBounds(0, 430, 420, 5);
        add(mainLabel);

        if(editType == "ADD"){
            mainBtn = new JButton("Add Note");
            mainBtn.setFont(btnFont);
            mainBtn.setBounds(100, 440, 200, 50);
            mainBtn.addActionListener(actionListener);
            add(mainBtn);
        }else if(editType == "UPDATE"){
            mainBtn = new JButton("Update Note");
            mainBtn.setFont(btnFont);
            mainBtn.setBounds(100, 440, 200, 50);
            mainBtn.addActionListener(actionListener);
            add(mainBtn);
        }else{
            mainBtn = new JButton("Close Note");
            mainBtn.setFont(btnFont);
            mainBtn.setBounds(100, 440, 200, 50);
            mainBtn.addActionListener(actionListener);
            add(mainBtn);
        }

        setLayout(null);
        setSize(420,525);
        setLocation(500, 150);
        setVisible(true);
    }
}
