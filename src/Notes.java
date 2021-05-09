public class Notes {
    int noteID;
    String noteTitle, noteText;

    public Notes(int noteID, String noteTitle, String noteText) {
        this.noteID = noteID;
        this.noteTitle = noteTitle;
        this.noteText = noteText;
    }

    public int getNoteID() {
        return noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
