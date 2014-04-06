public class ChordPlayer {
	private Game game;
	private MidiPlayer mp;
	private Interpreter i;
	
	public ChordPlayer(Game g) {
		this.game = g;
		mp = new MidiPlayer();
		i = g.getInterpreter();
	}
	
	public void play_chord(Chord c) {
		if (c == null || c.getNotes() == null) return;
		for (int i = 0; i < c.getNotes().length; i++) {
			mp.play_note(c.getNotes()[i] + 60, 127);
			game.notePlayed(c.getNotes()[i] + 60, 1);
		}
		stop_chord(c);
	}
	
	public void stop_chord(Chord c) {
		if (c == null || c.getNotes() == null) return;
		for (int i = 0; i < c.getNotes().length; i++) {
			mp.stop_note(c.getNotes()[i] + 60, 127);
			game.noteReleased(c.getNotes()[i] + 60);
		}
	}
}