

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;

public class HelloAgiScript extends BaseAgiScript {

	@Override
	public void service(AgiRequest arg0, AgiChannel arg1) throws AgiException {
		{
			// Answer the channel...
			//answer();
			// ...say hello...
			//streamFile("hello-world");
			exec("System","echo Apple Mango Banana | /usr/bin/text2wave -scale 1.5 -F 8000 -o /tmp/festival.wav");
			/*exten => 1000,1,Answer()
			exten => 1000,2,System(echo "Hey we are waiting for you in the meeting" | /usr/bin/text2wave -scale 1.5 -F 8000 -o /tmp/festival.wav)
			exten => 1000,3,Playback(/tmp/festival)
			exten => 1000,4,System(rm /tmp/festival.wav)
			exten => 1000,5,Hangup()*/

			// ...and hangup.
			//hangup();
		}

	}

}
