import java.util.ArrayList;
import java.util.List;

/**
 *
 * proof of concept for fretboard component: manages number of strings and frets
 * on a stringed instrument, and the tuning of the strings.
 *
 *
 * @author Noah Rhea
 *
 */
public class FretboardDemo {

    /**
     * Nested types and constants.
     */

    public enum Note {
        /** Standard Notes in English music. */
        C, C_SHARP, D, D_SHARP, E, F, F_SHARP, G, G_SHARP, A, A_SHARP, B;

        /** Flat Notes of comparable sharp notes. */
        public static final Note D_FLAT = C_SHARP;
        /** E flat to D Sharp. */
        public static final Note E_FLAT = D_SHARP;
        /** G flat to F sharp. */
        public static final Note G_FLAT = F_SHARP;
        /** A flat to G sharp. */
        public static final Note A_FLAT = G_SHARP;
        /** B flat to A sharp. */
        public static final Note B_FLAT = A_SHARP;
    }

    /**
     * Location on the fretboard by string and fret.
     */
    public static class Position {
        /** Number of strings. */
        private final int string;
        /** Number of fret. */
        private final int fret;

        /**
         * @param string
         * @param fret
         */
        public Position(int string, int fret) {
            this.string = string;
            this.fret = fret;
        }

        /**
         * @return string
         */
        public final int string() {
            return this.string;
        }

        /**
         * @return fret
         */
        public final int fret() {
            return this.fret;
        }

        @Override
        public final String toString() {
            return "s" + this.string + ":f" + this.fret;
        }
    }

    /** Standard tuning for a guitar. */
    private static final Note[] STANDARD_TUNING = { Note.E, Note.A, Note.D,
            Note.G, Note.B, Note.E };
    /** Base number of frets. */
    public static final int BASE_FRET = 20;
    /** Notes in an octave. */
    public static final int OCTAVE = 12;

    /** number of frets on the instrument. */
    private int frets;
    /** Tuning of each string from top to bottom. */
    private Note[] tuning;

    /**
     * Constructors.
     */

    public FretboardDemo() {
        this(STANDARD_TUNING, BASE_FRET);
    }

    /**
     * @param tuning
     *            open note of each string
     * @param frets
     *            number of frets
     * @requires tuning.length > 0 and frets > 0
     */
    public FretboardDemo(Note[] tuning, int frets) {
        assert tuning.length > 0 : "Violation of: at least one string.";
        assert frets > 0 : "Violation of: at least one fret.";
        this.tuning = tuning.clone();
        this.frets = frets;
    }

    /**
     * Secondary Methods
     */

    /**
     * Finds the positions of a given note on a given string of an instrument.
     *
     * @param n
     * @param string
     * @return an arrayList of positions that have the value of note n.
     */
    public List<Position> findPositions(Note n, int string) {
        //NOT USED FOR DEMO
        return null;
    }

    /**
     * Finds the positions of a given note on all strings of an instrument.
     *
     * @param n
     * @return an arrayList of Positions that have the value of note n.
     */
    public List<Position> findPositions(Note n) {
        List<Position> result = new ArrayList<>();
        for (int i = 0; i < this.stringCount(); i++) { //for each string
            for (int j = 0; j <= this.fretCount(); j++) { //for each fret
                if (n == this.noteAt(i, j)) {
                    Position p = new Position(i, j);
                    result.add(p);
                }
            }
        }
        return result;

    }

    /**
     * Finds the note value at a certain fret of a certain string.
     *
     * @param string
     * @param fret
     * @return the note of the position
     */
    public Note noteAt(int string, int fret) {
        assert (fret <= this.fretCount())
                && (fret >= 0) : "Violation of: Fret does not exist.";
        assert (string <= this.stringCount())
                && (string >= 0) : "Violation of: String does not exist.";
        Note n = this.tuningOf(string); //returns Note
        int pos = n.ordinal(); //position in notes of base note
        pos += fret; // pos is original position of note + fret number
        pos = pos % OCTAVE; //pos is enum value of Note
        n = Note.values()[pos]; //sets n to the correct note
        return n;
    }

    /**
     * Kernel Methods
     */

    /**
     * Changes the tuning of a string to the given note n.
     *
     * @param string
     * @param n
     */
    public void changeTuning(int string, Note n) {
        //NOT USED FOR DEMO
    }

    /**
     * Counts the number of strings.
     *
     * @return an integer of the number of strings.
     */
    public int stringCount() {
        return this.tuning.length;
    }

    /**
     * Counts the number of frets.
     *
     * @return an integer of the number of frets.
     */
    public int fretCount() {
        return this.frets;
    }

    /**
     * @param string
     * @return the tuning of given string as a note.
     */
    public Note tuningOf(int string) {
        Note n = this.tuning[string];
        return n;
    }

    /**
     * Main Demo
     */

    /**
     * @param args
     */
    public static void main(String[] args) {
        int frets = 22;
        Note[] dropD = { Note.D, Note.A, Note.D, Note.G, Note.B, Note.E };
        FretboardDemo guitar = new FretboardDemo(dropD, frets);
        System.out.println(guitar.tuningOf(1)); //A
        System.out.println(guitar.fretCount()); //22
        System.out.println(guitar.stringCount()); //6
        System.out.println(guitar.noteAt(5, 18)); //A_SHARP
        List<Position> findA = guitar.findPositions(Note.D_SHARP);
        for (int i = 0; i < findA.size(); i++) { //print every position of note A
            Position curr = findA.get(i);
            System.out.print(curr.toString() + ", ");
        }

    }

}
