package br.com.gittanger.model;

import lombok.Data;

@Data
public class GitBranch {
	private String newBranch;
	private String baseBranch;
	private String path;
	private String user;
	private String credentials;

}
