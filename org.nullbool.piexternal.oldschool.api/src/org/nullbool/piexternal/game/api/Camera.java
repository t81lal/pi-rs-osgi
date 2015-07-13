package org.nullbool.piexternal.game.api;

import org.nullbool.piexternal.game.api.meta.RSTile;
import org.nullbool.piexternal.game.api.wrappers.entity.Actor;
import org.nullbool.piexternal.game.api.wrappers.entity.Player;
import org.nullbool.piexternal.game.api.wrappers.world.GameObject;

public class Camera {

	private int normalizeAngle(int angle) {
		return angle % 360;
	}

	private int getDiff(int curAngle, int angle) {
		int difference = this.normalizeAngle(Math.abs(angle - curAngle));
		return difference > 180 ? 360 - difference : difference;
	}

	private int angle() {
		return Math.abs((int) ((double) OldschoolClient.getCameraYaw() / 5.68D) - 360);
	}

	public void turnAngleTo(GameObject object) {
		this.turnAngleTo(object.getTile());
	}

	public void turnAngleTo(Actor<?> actor) {
		this.turnAngleTo(actor.getTile());
	}

	public void turnAngleTo(RSTile tile) {
		int angle = this.getAngleTo(tile);
		angle += angle > 180 ? -180 : 180;
		this.setAngle(angle);
	}

	public void setAngle(int angle) {
		int curAngle = this.angle();
		Timer time = new Timer(3500L);
		int diff = this.getDiff(curAngle, angle);
		int a = this.normalizeAngle(curAngle + diff);
		int e = a == curAngle ? 37 : 39;
		Keyboard keyboard = Keyboard.get();
		keyboard.sendEvent((char) e, true);

		while (time.isRunning() && Math.abs(this.angle() - angle) > 10) {
			Utilities.sleep(15L);
		}

		keyboard.sendEvent((char) e, false);
	}

	public void setPitch(int pitch) {
		Timer time = new Timer(3500L);
		boolean cond = OldschoolClient.getCameraPitch() > pitch;
		int e = cond ? 37 : 39;
		Keyboard keyboard = Keyboard.get();
		keyboard.sendEvent((char) e, true);

		while (time.isRunning() && OldschoolClient.getCameraPitch() != pitch) {
			Utilities.sleep(15L);
		}

		keyboard.sendEvent((char) e, false);
	}

	public int getAngleTo(int d) {
		int a = this.angle() + (this.angle() < d ? 360 : 0);
		return a - d - (a - d > 180 ? 360 : 0);
	}

	public int getAngleTo(RSTile tile) {
		Player player = OldschoolClient.getLocalPlayer();
		double vx = (double) (tile.getX() - player.getTileX());
		double vy = (double) (player.getTileY() - tile.getY());
		double angle = 360.0D - Math.toDegrees(Math.atan2(vx, vy));
		return (int) (angle - (double) (angle >= 360.0D ? 360 : 0));
	}
}
