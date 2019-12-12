package sourceCode;

public class sex {
	private String sexs;

	public String getSexs() {
		return sexs;
	}

	public void setSexs(String sexs) {
		this.sexs = sexs;
	}

	public sex(String sexs) {
		super();
		this.sexs = sexs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sexs == null) ? 0 : sexs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		sex other = (sex) obj;
		if (sexs == null) {
			if (other.sexs != null)
				return false;
		} else if (!sexs.equals(other.sexs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "sex [sexs=" + sexs + "]";
	}
	
}
