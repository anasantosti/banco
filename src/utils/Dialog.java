package utils;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Dialog {

	public static void mostrarMensagem(String mensagem, String titulo, int tipoMensagem) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, tipoMensagem);
	}

	public static void mostrarMensagemInfo(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Informação", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void mostrarMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static String obterDado(String mensagem) throws Exception {
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
		UIManager.put("OptionPane.okButtonText", "Ok");
		UIManager.put("OptionPane.inputDialogTitle", "Informe");

		String dado = JOptionPane.showInputDialog(mensagem);
		if (dado == null)
			throw new Exception("apertouCancelar");
		return dado;
	}

	public static Object obterDadoComOpcoes(String mensagem, List<String> opcoes) {
		Object dado = JOptionPane.showInputDialog(null, mensagem, "Informe", JOptionPane.QUESTION_MESSAGE, null,
				opcoes.toArray(), "0");
		return dado;
	}
}
