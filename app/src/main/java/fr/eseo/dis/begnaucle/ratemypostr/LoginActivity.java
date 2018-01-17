package fr.eseo.dis.begnaucle.ratemypostr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.eseo.dis.begnaucle.ratemypostr.model.User;
import fr.eseo.dis.begnaucle.ratemypostr.service.LogonServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.LogonServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.ServiceResult;

public class LoginActivity extends AbstractActivity{


    EditText userField = null;
    EditText passField = null;
    Button loginButton = null;
    TextView logTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_login);
        userField = (EditText) findViewById(R.id.Login_UserEditText);
        passField = (EditText) findViewById(R.id.Login_PassEditText);
        userField.setText("schandan");
        passField.setText("aWzsD7tjBboI");

        loginButton = (Button) findViewById(R.id.Login_LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeSubjects();

                LogonServiceClient logonService = new LogonServiceClient(context, userField.getText().toString(), passField.getText().toString());
                //LogonService = LogonServiceClient.connectGutowNic(context);
                LogonServiceClient.connectGutowNic(context);
                addSubject(logonService);

                Thread logonThread = new Thread(logonService);
                logonThread.start();
            }
        });

        logTextView = (TextView) findViewById(R.id.Login_LogTextView);

    }

    @Override
    public void receiveResult(ServiceResult sr) {
        switch (sr.getClass().getSimpleName()) {
            case "LogonServiceResult":
                User.setConnectedUser(((LogonServiceResult) sr).getUser());
                if(User.getConnectedUser() != null){
                    System.out.println("NOUVEL UTILISATEUR : " + User.getConnectedUser().toString());
                    if(User.getConnectedUser() != null){
                        Intent intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
                break;
            default:
                logTextView.setText("Invalid Credentials \"" +
                        userField.getText().toString()+ " " +
                        passField.getText().toString() +
                        "\"");
                break;
        }
    }
}
