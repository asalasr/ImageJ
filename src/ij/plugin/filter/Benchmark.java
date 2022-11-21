package ij.plugin.filter;
import ij.*;
import ij.process.*;

/** Implements the Plugins/Utilities/Run Benchmark command when
	the current image is 512x512 and RGB. Results and additional
	benchmarks are available at<br> 
	"http://imagej.nih.gov/ij/plugins/benchmarks.html". */
public class Benchmark implements PlugInFilter{

	String arg;
	ImagePlus imp;
	boolean showUpdates= true;
	int counter;
	
	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_ALL+NO_CHANGES+SNAPSHOT;
	}

	public void run(ImageProcessor ip) {
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		ip.setInterpolate(false);
		for (int i=0; i <4; i++) {
			ip.invert();
			updateScreen(imp);
		}
		for (int i=0; i <4; i++) {
			ip.flipVertical();
			updateScreen(imp);
		}
		ip.flipHorizontal(); updateScreen(imp);
		ip.flipHorizontal(); updateScreen(imp);
		for (int i=0; i <6; i++) {
			ip.smooth();
			updateScreen(imp);
		}
		ip.reset();
		for (int i=0; i <6; i++) {
			ip.sharpen();
			updateScreen(imp);
		}
		ip.reset();
		ip.smooth(); updateScreen(imp);
		ip.findEdges(); updateScreen(imp);
		ip.invert(); updateScreen(imp);
		ip.autoThreshold(); updateScreen(imp);
		ip.reset();
		ip.medianFilter(); updateScreen(imp);
		for (int i=0; i <360; i +=15) {
			ip.reset();
			ip.rotate(i);
			updateScreen(imp);
		}
		double scale = 1.5;
		for (int i=0; i <8; i++) {
			ip.reset();
			ip.scale(scale, scale);
			updateScreen(imp);
			scale = scale*1.5;
		}
		for (int i=0; i <12; i++) {
			ip.reset();
			scale = scale/1.5;
			ip.scale(scale, scale);
			updateScreen(imp);
		}
		ip.reset();
		updateScreen(imp);
	}
	
	void updateScreen(ImagePlus imp) {
		if (showUpdates)
			imp.updateAndDraw();
		IJ.showStatus((counter++) + "/"+72);
	}

}


