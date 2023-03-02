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

	public String checkoutProject(String branch) {
		try {
			CheckoutCommand checkoutCommand = git.checkout().setName(branch);
			checkoutCommand.call();
			return "Checkout executado com sucesso para branch: " + branch;

		} catch (GitAPIException e) {
			e.printStackTrace();
			return "Erro ao fazer checkout para branch: " + branch ;
		}
	}

	public String pullProject(String user, String credentials) {
		try {
			PullCommand pullCommand = git.pull();
			pullCommand.setCredentialsProvider(authenticate(user, credentials));
			pullCommand.call();
			return "Branch atualizada com sucesso";

		} catch (GitAPIException e) {
			e.printStackTrace();
			return "Erro ao atualizar a branch.";
		}
	}

	public String createBranch(String branch) {
		try {
			CreateBranchCommand branchCommand = git.branchCreate().setName(branch);
			branchCommand.call();
			return "Branch: " + branch + ", criada com sucesso";

		} catch (GitAPIException e) {
			e.printStackTrace();
			return "Erro ao criar a branch: " + branch + " " + e.getMessage();
		}
	}
	
	public CredentialsProvider authenticate(String user, String credentials){
		return new UsernamePasswordCredentialsProvider(user, credentials);
	}
}
