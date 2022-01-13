package cybersoft.javabackend.gamedoanso.model;

import java.time.LocalDateTime;

import cybersoft.javabackend.gamedoanso.util.DateUtils;

public class GameRecord {
	private int id = 1;
	private Player player;
	private int number;
	private int point;
	private String startDate;
	private String finishDate;
	private boolean isFinished;
	
	private static int _recordNumbers = 1;
	
	public GameRecord(Player player) {
		id = _recordNumbers++;
		this.player = player;
		number = (int) (Math.random() * 1000) + 1;
		point = 0;
		startDate = DateUtils.toString(LocalDateTime.now());
		isFinished = false;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
}
