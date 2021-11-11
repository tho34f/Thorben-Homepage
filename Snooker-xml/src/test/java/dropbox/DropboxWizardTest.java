package dropbox;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.dropbox.core.DbxException;
import com.thorben.helloworld.dropbox.DropboxWizard;

public class DropboxWizardTest {

	@Test
	public void dropboxTest() throws DbxException, IOException {
		
		DropboxWizard dbw = new DropboxWizard();
		String accountInformation = dbw.getAccountInformation();
		assertEquals("{\"account_id\":\"dbid:AAAxNF99bg1lnjcv6vVrakYWAOSsjykgCuo\",\"name\":{\"given_name\":\"Thorben\",\"surname\":\"Dierkes\",\"familiar_name\":\"Thorben Dierkes\",\"display_name\":\"Thorben Dierkes\",\"abbreviated_name\":\"TD\"},\"email\":\"thorben.dierkes@gmail.com\",\"email_verified\":true,\"disabled\":false,\"locale\":\"de\",\"referral_link\":\"https://www.dropbox.com/referrals/AAB0Wdts8QpsvyQheZVnDTO3LNmBubo22HQ?src=app9-9991920\",\"is_paired\":false,\"account_type\":\"basic\",\"root_info\":{\".tag\":\"user\",\"root_namespace_id\":\"9104112256\",\"home_namespace_id\":\"9104112256\"},\"country\":\"DE\"}", accountInformation);
		
		boolean isOk = dbw.checkFolder("Backen");
		assertEquals(false, isOk);
		List<String> folders = dbw.listDropboxFolders("Backen");
		assertEquals(true, !folders.isEmpty());
		
		boolean isDownloadOk = dbw.downloadFromDropbox("Backen", "Apfelkuchen");
		assertEquals(true, isDownloadOk);
	}
}
