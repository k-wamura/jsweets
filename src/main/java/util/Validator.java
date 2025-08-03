package util;

import java.util.ArrayList;
import java.util.List;

import model.entity.Contact;
import model.entity.User;

public class Validator {
	
	/**
	 * 新規登録の入力値チェック
	 * 
	 * @param user
	 * @return
	 */
	public static List<String> registValidate(User user){
		List<String> errors = new ArrayList<>();
		String lName = user.getlName();
		String fName = user.getfName();
		String lNameKana = user.getlNameKana();
		String fNameKana = user.getfNameKana();
		String password = user.getPassword();
		String prefecture = user.getPrefecture();
		String city = user.getCity();
		String oAddress = user.getoAddress();
		String tel = user.getTel();
		String email = user.getEmail();
		

		// 姓・名：未入力チェック
		if (lName == null || lName.trim().isEmpty()) {
		    errors.add("姓を入力してください。");
		}
		if (fName == null || fName.trim().isEmpty()) {
		    errors.add("名を入力してください。");
		}

		// カナ：未入力 + 全角カタカナチェック
		if (lNameKana == null || lNameKana.trim().isEmpty()) {
		    errors.add("姓（カナ）を入力してください。");
		} else if (!lNameKana.matches("^[\\u30A0-\\u30FF]+$")) {
		    errors.add("姓（カナ）は全角カタカナで入力してください。");
		}

		if (fNameKana == null || fNameKana.trim().isEmpty()) {
		    errors.add("名（カナ）を入力してください。");
		} else if (!fNameKana.matches("^[\\u30A0-\\u30FF]+$")) {
		    errors.add("名（カナ）は全角カタカナで入力してください。");
		}

		// パスワード：未入力 + 長さチェック
		if (password == null || password.isEmpty()) {
		    errors.add("パスワードを入力してください。");
		} else if (password.length() < 6) {
		    errors.add("パスワードは6文字以上で入力してください。");
		}

		// 都道府県・市区町村・番地：未入力チェック
		if (prefecture == null || prefecture.trim().isEmpty()) {
		    errors.add("都道府県を選択してください。");
		}
		if (city == null || city.trim().isEmpty()) {
		    errors.add("市区町村を入力してください。");
		}
		if (oAddress == null || oAddress.trim().isEmpty()) {
		    errors.add("番地・建物名などを入力してください。");
		}

		// 電話番号：未入力 + 数字とハイフンのみ
		if (tel == null || tel.trim().isEmpty()) {
		    errors.add("電話番号を入力してください。");
		} else if (!tel.matches("^\\d{2,4}-\\d{2,4}-\\d{3,4}$")) {
		    errors.add("電話番号の形式が正しくありません。例: 090-1234-5678");
		}

		// メールアドレス：未入力 + 簡易形式チェック
		if (email == null || email.trim().isEmpty()) {
		    errors.add("メールアドレスを入力してください。");
		} else if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$")) {
		    errors.add("メールアドレスの形式が正しくありません。");
		}
		
		return errors;
	}
	
	
	public static List<String> contactValidate(Contact contact){
		List<String> errors = new ArrayList<>();
		
		int item = contact.getContactItem();
        String name = contact.getName();
        String prefecture = contact.getPrefecture();
        String mail = contact.getMail();
        String message = contact.getMessage();

		if (item == 0) {
		    errors.add("お問い合わせ内容を選択してください。");
		}
		if (name == null || name.trim().isEmpty()) {
		    errors.add("お名前を入力してください。");
		}
		if (prefecture == null || prefecture.trim().isEmpty()) {
		    errors.add("都道府県を選択してください。");
		}
		if (mail == null || mail.trim().isEmpty()) {
		    errors.add("メールアドレスを入力してください。");
		} else if (!mail.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
		    errors.add("正しいメールアドレスの形式で入力してください。");
		}
		if (message == null || message.trim().isEmpty()) {
		    errors.add("お問い合わせ内容を入力してください。");
		}
		
		return errors;
	}
}
