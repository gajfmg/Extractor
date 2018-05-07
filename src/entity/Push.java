package entity;

public class Push {
	private String sha;
	private Commit commit;
	private String url;
	private String html_url;
	private Author author;
	private Commiter commiter;
	
	
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public Commit getCommit() {
		return commit;
	}
	public void setCommit(Commit commit) {
		this.commit = commit;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public Author getAutor() {
		return author;
	}
	public void setAutor(Author autor) {
		this.author = autor;
	}
	public Commiter getCommiter() {
		return commiter;
	}
	public void setCommiter(Commiter commiter) {
		this.commiter = commiter;
	}
	
	
}
