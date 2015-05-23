package mastCam.hardware;

public class Camera {
	private CameraTypes type;
	private String focalLength;
	private String lens;
	private String fov;
	private String resolution;
	private String currentFilter;
	private int numberOfFilters;
	
	public Camera(CameraTypes _Type){
		this.type = _Type;
		if(_Type == CameraTypes.MastCam100){
			focalLength = "100 mm";
			lens = "f/10";
			fov = "5.1 degrees";
			resolution = "1200 x 1200";
			//currentFilter = ;
		}
		else if(_Type == CameraTypes.MastCam34){
			focalLength = "34 mm";
			lens = "f/8";
			fov = "15 degrees";
			resolution = "1200 x 1200";
		}
		numberOfFilters = 8;
	}

	public CameraTypes getType() {
		return type;
	}

	public void setType(CameraTypes type) {
		this.type = type;
	}

	/**
	 * @return the focalLength
	 */
	public String getFocalLength() {
		return focalLength;
	}

	/**
	 * @param focalLength the focalLength to set
	 */
	public void setFocalLength(String focalLength) {
		this.focalLength = focalLength;
	}

	/**
	 * @return the lens
	 */
	public String getLens() {
		return lens;
	}

	/**
	 * @param lens the lens to set
	 */
	public void setLens(String lens) {
		this.lens = lens;
	}

	/**
	 * @return the fov
	 */
	public String getFov() {
		return fov;
	}

	/**
	 * @param fov the fov to set
	 */
	public void setFov(String fov) {
		this.fov = fov;
	}

	/**
	 * @return the resolution
	 */
	public String getResolution() {
		return resolution;
	}

	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	/**
	 * @return the currentFilter
	 */
	public String getCurrentFilter() {
		return currentFilter;
	}

	/**
	 * @param currentFilter the currentFilter to set
	 */
	public void setCurrentFilter(String currentFilter) {
		this.currentFilter = currentFilter;
	}

	/**
	 * @return the numberOfFilters
	 */
	public int getNumberOfFilters() {
		return numberOfFilters;
	}

	/**
	 * @param numberOfFilters the numberOfFilters to set
	 */
	public void setNumberOfFilters(int numberOfFilters) {
		this.numberOfFilters = numberOfFilters;
	}


}
