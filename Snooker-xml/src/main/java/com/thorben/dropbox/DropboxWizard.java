package com.thorben.dropbox;

import java.io.File;
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
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.SearchV2Result;
import com.dropbox.core.v2.users.FullAccount;

public class DropboxWizard {
	
	private static final String PDF = ".pdf";
	private static final String BACKSLASH = "/";
	private DbxClientV2 dbxClient;

	public DropboxWizard() {
		// Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        this.dbxClient = new DbxClientV2(config, ACCESS_TOKEN);
	}
	
	public String getAccountInformation() throws DbxException {
		FullAccount account = this.dbxClient.users().getCurrentAccount();
		return account.toString();
	}

	public boolean uploadToDropbox(String fileName) throws IOException, DbxException {
		FileMetadata uploadFile = null;
		boolean isUploadOk = false;
		try(InputStream in = new FileInputStream(fileName)){
			uploadFile = this.dbxClient.files().uploadBuilder(BACKSLASH + fileName).uploadAndFinish(in);
			isUploadOk = uploadFile.getIsDownloadable();
		}
		
		return isUploadOk;
	}

	public List<String> listDropboxFolders(String folderPath) throws DbxException {
		List<String> folders = new ArrayList<>();
		ListFolderResult listing = this.dbxClient.files().listFolderBuilder(BACKSLASH + folderPath).start();
		for(Metadata listItem : listing.getEntries()) {
			folders.add(listItem.getPathDisplay());
		}
		return folders;
	}

	public boolean createFolder(String folderName) throws DbxException {
		CreateFolderResult folder = this.dbxClient.files().createFolderV2(BACKSLASH + folderName);
		FolderMetadata fmd = folder.getMetadata();
		return fmd.getName().equals(folderName);
	}
	
	public boolean checkFolder(String folderPath) throws DbxException {
		SearchV2Result search = this.dbxClient.files().searchV2Builder(folderPath).start();
		return search.getHasMore();
	}

	public boolean downloadFromDropbox(String fileName, String dataName) throws DbxException, IOException {
		FileMetadata downloadedFile = null;
		boolean isDownloadOk = false;
		String userName = System.getProperty("user.name");
		File downloadPath = new File("C:/Users/" + userName + "/Documents/" + dataName + PDF);
		try(FileOutputStream outputStream = new FileOutputStream(downloadPath)) {
			downloadedFile = this.dbxClient.files().downloadBuilder(BACKSLASH +  fileName + BACKSLASH + dataName + PDF).download(outputStream);
			isDownloadOk = downloadedFile.getIsDownloadable();
		}
		
		return isDownloadOk;
	}

}
