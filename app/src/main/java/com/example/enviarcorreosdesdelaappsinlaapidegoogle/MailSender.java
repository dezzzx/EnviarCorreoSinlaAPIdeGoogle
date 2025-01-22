package com.example.enviarcorreosdesdelaappsinlaapidegoogle;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    private static final String EMAIL = "correo@gmail.com";//Aqui pones el correo de gmail
    private static final String PASSWORD = "contraseña de aplicacion";    // Cambia esto obteniendo la contraseña de aplicacion mediante este (El correo es recomendable que tenga verificacion de dos pasos) Link: https://myaccount.google.com/apppasswords?rapt=AEjHL4OUljFqUrXMsKkNLaFF4R9Gb7yo10nru5LzetNSxGYKEO8GkBSopOaqtN9j8YTrro6PKNT1iAnoC3LpJrs4-5Clz61oNTOGtdzAwMs-USlHXsPZQ30
    public static void enviarCorreo(String destinatario, String asunto, String mensaje) {
        AsyncTask.execute(() -> {
            try {
                // Configuración de propiedades para SMTP
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                // Crear una sesión con autenticación
                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL, PASSWORD);
                    }
                });

                // Crear el mensaje
                Message emailMessage = new MimeMessage(session);
                emailMessage.setFrom(new InternetAddress(EMAIL));
                emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
                emailMessage.setSubject(asunto);
                emailMessage.setText(mensaje);

                // Enviar el correo
                Transport.send(emailMessage);
                Log.i("MailSender", "Correo enviado con éxito a " + destinatario);
            } catch (Exception e) {
                Log.e("MailSender", "Error al enviar el correo: " + e.getMessage(), e);
            }
        });
    }
}
