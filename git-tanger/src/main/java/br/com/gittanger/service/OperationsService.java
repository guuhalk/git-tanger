package br.com.gittanger.service;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import br.com.gittanger.model.GitBranch;

public class OperationsService {

	private Git git;
	private Repository getRepoProject;

	public OperationsService(GitBranch gitBranch) throws IOException, GitAPIException {
		this.getRepoProject = new FileRepositoryBuilder().setGitDir(new File(gitBranch.getPath().concat("\\.git"))).build();
		this.git = new Git(getRepoProject);
	}

	public void checkoutProject(String branch) {
		try {
			CheckoutCommand checkoutCommand = git.checkout().setName(branch);
			checkoutCommand.call();

		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	public void pullProject(String user, String credentials) {
		try {
			PullCommand pullCommand = git.pull();
			pullCommand.setCredentialsProvider(authenticate(user, credentials));
			pullCommand.call();

		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	public void createBranch(String branch) {
		try {
			CreateBranchCommand branchCommand = git.branchCreate().setName(branch);
			branchCommand.call();

		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}
	
	public CredentialsProvider authenticate(String user, String credentials){
		return new UsernamePasswordCredentialsProvider(user, credentials);
	}
}
