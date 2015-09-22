package br.com.livroandroid.playermp3;

/**
 * Interface para fazer o bind do Service MediaPlayerService que a implementa
 * 
 * @author ricardo
 *
 */
public interface InterfaceMp3 {
	// Inicia a música
	void play(String mp3);
	// Faz pause da música
	void pause();
	// Para a música
	void stop();
	// true se está tocando
	boolean isPlaying();
	// Caminho da música
	String getMp3();
}
