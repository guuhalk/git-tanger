package br.com.gittanger.service;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CherryPickCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import br.com.gittanger.model.GitCherryPick;

public class OperationCherryPickService {

	private Git git;
	private Repository getRepoProject;

	public OperationCherryPickService(GitCherryPick gitCherryPick) throws IOException, GitAPIException {
		this.getRepoProject = new FileRepositoryBuilder().setGitDir(new File(gitCherryPick.getPath().concat("\\.git"))).build();
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

	public String cherryPickProject(GitCherryPick gitCherryPick) throws IOException {
		try {
			Ref ref = getRepoProject.getRef(gitCherryPick.getBaseBranch());

			CheckoutCommand checkoutCommand = git.checkout().setName(gitCherryPick.getOriginBranch());
			checkoutCommand.call();

			CherryPickCommand cherryPickCommand = git.cherryPick().include(ref);

			cherryPickCommand.call();
			
			return "Cherry pick feito com sucesso da branch " + gitCherryPick.getBaseBranch() +
					", para branch " + gitCherryPick.getOriginBranch();
			
		} catch (GitAPIException e) {
			e.printStackTrace();
			return "Erro ao fazer o cherry pick";
		}
	}

}
