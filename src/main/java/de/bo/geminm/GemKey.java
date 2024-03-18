package de.bo.geminm;

import java.util.Objects;

public class GemKey {

	private final String plz;

	private final String ort;

	public GemKey(String plz, String ort) {
		this.plz = plz;
		this.ort = ort;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ort, plz);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GemKey other = (GemKey) obj;
		return Objects.equals(ort, other.ort) && Objects.equals(plz, other.plz);
	}

}
