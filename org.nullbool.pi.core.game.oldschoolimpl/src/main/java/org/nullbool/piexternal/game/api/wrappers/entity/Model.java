package org.nullbool.piexternal.game.api.wrappers.entity;

import org.nullbool.piexternal.game.api.accessors.entity.IModel;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:13:07 <br>
 */
public class Model extends Renderable<IModel> implements IModel {

//	private Point point;
//	private int[] orginal_x;
//	private int[] orginal_z;
//	private int orientation;
//	private int gridX;
//	private int gridY;
//	private int[] verticesX;
//	private int[] verticesZ;
//	private int[] verticesY;
//	private int[] trianglesX;
//	private int[] trianglesZ;
//	private int[] trianglesY;

	public Model(IModel _model) {
		super(_model);
	}

	@Override
	public int getIndicesCount() {
		return _node.getIndicesCount();
	}

	@Override
	public int[] getIndicesX() {
		return getIndicesX();
	}

	@Override
	public int[] getIndicesY() {
		return getIndicesY();
	}

	@Override
	public int[] getIndicesZ() {
		return getIndicesZ();
	}

	@Override
	public int getTriangleCount() {
		return getTriangleCount();
	}

	@Override
	public int getVertexCount() {
		return getVertexCount();
	}

	@Override
	public int[] getVerticesX() {
		return getVerticesX();
	}

	@Override
	public int[] getVerticesY() {
		return getVerticesY();
	}

	@Override
	public int[] getVerticesZ() {
		return getVerticesZ();
	}
//
//	public Polygon[] getTriangles() {
//		LinkedList<Polygon> polygons = new LinkedList<Polygon>();
//		int[] indices1 = this.trianglesX;
//		int[] indices2 = this.trianglesY;
//		int[] indices3 = this.trianglesZ;
//		int[] xPoints = this.verticesX;
//		int[] yPoints = this.verticesY;
//		int[] zPoints = this.verticesZ;
//
//		for(int i = 0; i < indices1.length; ++i) {
//			int z1 = -yPoints[indices1[i]];
//			int z2 = -yPoints[indices2[i]];
//			int z3 = -yPoints[indices3[i]];
//			int x1 = this.gridX + xPoints[indices1[i]];
//			int x2 = this.gridX + xPoints[indices2[i]];
//			int x3 = this.gridX + xPoints[indices3[i]];
//			int y1 = this.gridY + zPoints[indices1[i]];
//			int y2 = this.gridY + zPoints[indices2[i]];
//			int y3 = this.gridY + zPoints[indices3[i]];
//			Point p1 = Calculations.pointByCam(x1, y1, z1);
//			Point p2 = Calculations.pointByCam(x2, y2, z2);
//			Point p3 = this.api.calculations.pointByCam(x3, y3, z3);
//			if(p1.x >= 0 && p2.x >= 0 && p3.x >= 0) {
//				int[] a1 = new int[]{p1.x, p2.x, p3.x};
//				int[] a2 = new int[]{p1.y, p2.y, p3.y};
//				polygons.add(new Polygon(a1, a2, 3));
//			}
//		}
//
//		return (Polygon[])polygons.toArray(new Polygon[polygons.size()]);
//	}
//
//	private void initialize(int cos, int sin) {
//		IntStream x = IntStream.range(0, this.orginal_x.length);
//		IntStream z = IntStream.range(0, this.orginal_x.length);
//		x.forEach((i) -> {
//			this.verticesX[i] = this.orginal_x[i] * cos + this.orginal_z[i] * sin >> 15 >> 1;
//		});
//		z.forEach((i) -> {
//			this.verticesZ[i] = this.orginal_z[i] * cos - this.orginal_x[i] * sin >> 15 >> 1;
//		});
//	}
//
//	public Point getPoint() {
//		Point point = new Point(-1, -1);
//		Polygon[] triangles = this.getTriangles();
//		boolean tempo = triangles.length > 0;
//
//		for(int i = 0; tempo && i < 100 && !this.api.calculations.isOnScreen(point); ++i) {
//			Polygon p = triangles[Utilities.next(0, triangles.length)];
//			int x = p.xpoints[Utilities.next(0, p.xpoints.length)];
//			int y = p.ypoints[Utilities.next(0, p.ypoints.length)];
//			point = new Point(x, y);
//		}
//
//		return point;
//	}
//
//	public void draw(Graphics2D graphics, Color color) {
//		Stream stream = Arrays.stream(this.getTriangles());
//		stream.forEach((triangle) -> {
//			graphics.fillPolygon(triangle);
//		});
//	}
//
//	public int[] getXTriangles() {
//		return (int[])((Receiver)this.api.hooks.get("getIndicesX()")).value(this.model);
//	}
//
//	public int[] getYTriangles() {
//		return (int[])((Receiver)this.api.hooks.get("getIndicesY()")).value(this.model);
//	}
//
//	public int[] getZTriangles() {
//		return (int[])((Receiver)this.api.hooks.get("getIndicesZ()")).value(this.model);
//	}
//
//	public int[] getXVertices() {
//		return (int[])((Receiver)this.api.hooks.get("getVerticlesX()")).value(this.model);
//	}
//
//	public int[] getYVertices() {
//		return (int[])((Receiver)this.api.hooks.get("getVerticlesY()")).value(this.model);
//	}
//
//	public int[] getZVertices() {
//		return (int[])((Receiver)this.api.hooks.get("getVerticlesZ()")).value(this.model);
//	}
//
//	public boolean isOnScreen() {
//		return this.api.calculations.isOnScreen(this.point);
//	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IRenderable#setModelHeight(int)
	 */
	@Override
	public void setModelHeight(int var1) {
		_node.setModelHeight(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IModel#setVertexCount(int)
	 */
	@Override
	public void setVertexCount(int var1) {
		_node.setVertexCount(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IModel#setIndicesCount(int)
	 */
	@Override
	public void setIndicesCount(int var1) {
		_node.setIndicesCount(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IModel#setTriangleCount(int)
	 */
	@Override
	public void setTriangleCount(int var1) {
		_node.setTriangleCount(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IModel#setVerticesX(int[])
	 */
	@Override
	public void setVerticesX(int[] var1) {
		_node.setVerticesX(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IModel#setVerticesY(int[])
	 */
	@Override
	public void setVerticesY(int[] var1) {
		_node.setVerticesY(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IModel#setVerticesZ(int[])
	 */
	@Override
	public void setVerticesZ(int[] var1) {
		_node.setVerticesZ(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IModel#setIndicesX(int[])
	 */
	@Override
	public void setIndicesX(int[] var1) {
		_node.setIndicesX(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IModel#setIndicesY(int[])
	 */
	@Override
	public void setIndicesY(int[] var1) {
		_node.setIndicesY(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.entity.IModel#setIndicesZ(int[])
	 */
	@Override
	public void setIndicesZ(int[] var1) {
		_node.setIndicesZ(var1);		
	}

}