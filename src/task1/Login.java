package task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Login extends JFrame implements ActionListener {
    JButton loginButton = new JButton("Login");
    JTextField emailTextField = new JTextField();
    JPasswordField passwordTextField = new JPasswordField();
    JLabel emailJLabel = new JLabel("email");
    JLabel passwordJLabel = new JLabel("password");
    public Login() {
        super("Login");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        emailTextField.setPreferredSize(new Dimension(200,20));
        passwordTextField.setPreferredSize(new Dimension(200,20));
        loginButton.addActionListener(this);
        setPreferredSize(new Dimension(600,100));
        add(emailJLabel);
        add(emailTextField);
        add(passwordJLabel);
        add(passwordTextField);
        add(loginButton);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Game game = Game.getInstace();
        Account userAccount = null;
        List<Account> accountList = game.getAccountsList();
        // acum pot face dupa logare sa iti poti schimba parola si o modific in mapa si in lista

            for(Account account : accountList) {
                if(account.information.getCredentials().getParola().equals(passwordTextField.getText())
                && account.information.getCredentials().getEmail().equals(emailTextField.getText())){
                    userAccount = account;
                    break;
                }
            }
            if(userAccount == null ) {
                JOptionPane.showMessageDialog(this, "Nu exsita un cont cu acest email si parola. Incearca din nou!");
            } else {
                this.setVisible(false);
                JOptionPane.showMessageDialog(this,"Logare cu succes");
                List<Character> characterList = userAccount.personaje;

                CharactersList cl = new CharactersList(characterList);
            }


    }
}
