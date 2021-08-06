package com.thorben.helloworld.dropbox;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.SearchV2Result;
import com.dropbox.core.v2.users.FullAccount;

public class DropboxWizard {
	
	private static final String ACCESS_TOKEN = "kU34bXLSJfMAAAAAAAAAARLRIV8PFo_6IvKhSysNlTXO9mSDln6fUL-4eseEKmML";
	DbxClientV2 dbxClient;

	public DropboxWizard() {
		// Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        this.dbxClient = new DbxClientV2(config, ACCESS_TOKEN);
	}
	
	public String getAccountInformation() throws DbxException {
		FullAccount account = this.dbxClient.users().getCurrentAccount();
		return account.toString();
	}

	public void uploadToDropbox(String fileName) throws IOException, DbxException {
		try(InputStream in = new FileInputStream(fileName)){
			FileMetadata metadata = this.dbxClient.files().uploadBuilder("/" + fileName).uploadAndFinish(in);
		}
	}

	public List<String> listDropboxFolders(String folderPath) throws DbxException {
		List<String> folders = new ArrayList<>();
		ListFolderResult listing = this.dbxClient.files().listFolderBuilder("/" + folderPath).start();
		for(Metadata listItem : listing.getEntries()) {
			folders.add(listItem.getPathDisplay());
		}
		return folders;
	}

	public void createFolder(String folderName) throws DbxException {
		CreateFolderResult folder = this.dbxClient.files().createFolderV2("/" + folderName);
	}
	
	public boolean checkFolder(String folderPath) throws DbxException {
		SearchV2Result search = this.dbxClient.files().searchV2Builder(folderPath).start();
		return search.getHasMore();
	}

	public void downloadFromDropbox(String fileName) throws DbxException, IOException {
		try(FileOutputStream outputStream = new FileOutputStream(fileName)) {
			FileMetadata downloadedFile = this.dbxClient.files().downloadBuilder("/" + fileName).download(outputStream);
		}
	}

}
