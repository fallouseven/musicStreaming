package SERVICES_STREAM;

public class Music {
	private String name;
	private String genre;
	private String source;
	private String api;
	private int idTrack;
	public Music(String name,String genre,String source,String api,int idTrack){
		this.setName(name);
		this.setGenre(genre);
		this.setSource(source);
		this.setApi(api);
		this.setIdTrack(idTrack);
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public int getIdTrack() {
		return idTrack;
	}
	public void setIdTrack(int idTrack) {
		this.idTrack = idTrack;
	}

}
