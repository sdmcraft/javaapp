package misc;

import org.apache.sshd.ClientChannel;
import org.apache.sshd.ClientSession;
import org.apache.sshd.SshClient;
import org.apache.sshd.client.future.AuthFuture;
import org.apache.sshd.common.future.SshFutureListener;
import org.apache.sshd.common.util.NoCloseInputStream;
import org.apache.sshd.common.util.NoCloseOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by satyadeep on 6/10/15.
 */
public class SshClientDemo {
    public void sshWrapper() throws IOException, InterruptedException {
        SshClient client = SshClient.setUpDefaultClient();
        client.start();

        final ClientSession session = client.connect("root", "10.42.153.249", 22).await().getSession();

        int authState = ClientSession.WAIT_AUTH;
        while ((authState & ClientSession.WAIT_AUTH) != 0) {

            session.addPasswordIdentity("hello1234");

            System.out.println("authenticating...");
            final AuthFuture authFuture = session.auth();
            authFuture.addListener(new SshFutureListener<AuthFuture>()
            {
                public void operationComplete(AuthFuture arg0)
                {
                    System.out.println("Authentication completed with " + ( arg0.isSuccess() ? "success" : "failure"));
                }
            });

            authState = session.waitFor(ClientSession.WAIT_AUTH | ClientSession.CLOSED | ClientSession.AUTHED, 0);
        }

        if ((authState & ClientSession.CLOSED) != 0) {
            System.err.println("error");
            System.exit(-1);
        }

        final ClientChannel channel = session.createShellChannel();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        channel.setOut(new NoCloseOutputStream(outputStream));
        channel.setErr(new NoCloseOutputStream(errorStream));
        channel.open();

        executeCommand(channel, "pwd\n");
        executeCommand(channel, "ll\n");
        channel.waitFor(ClientChannel.CLOSED, 0);

        session.close(false);
        client.stop();

    }

    private static void executeCommand(final ClientChannel channel, final String command) throws IOException
    {
        final InputStream commandInput = new ByteArrayInputStream(command.getBytes());
        channel.setIn(new NoCloseInputStream(commandInput));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new SshClientDemo().sshWrapper();
    }


}
