<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<mandelbrot>
    <timestamp>2015-08-13 23:14:21</timestamp>
    <julia>true</julia>
    <point>0.3373675265352822</point>
    <point>-0.002084170785180396</point>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <source>fractal {
	orbit [-1.5 - 1.5i,+1.5 + 1.5i] [x,n] {
		trap circle0 [&lt;0.5,0&gt;] {
			MOVETO(&lt;-1,-1&gt;);
			LINETO(&lt;1,-1&gt;);
			LINETO(&lt;1,1&gt;);
			LINETO(&lt;-1,1&gt;);
			LINETO(&lt;-1,-1&gt;);
		}
		loop [0, 200] (mod2(x) &gt; 40) {
			x = x ^ 2.3 + w;
			if (circle0 ~? x) {
				stop;
			}
		}
	}
	color [(1,0.5,0.5,0.5)] {
		palette gradient {
			[#00000000 &gt; #FFFFFFFF, 10];
			[#FFFFFFFF &gt; #00000000, 60];
			[#00000000 &gt; #00000000, 130];
		}
		init {
			m = atan2(re(x),im(x)) / 2pi;
			if (m &lt; 0) {
				m = m + 1;
			}
		}
		rule (n &gt; 0) [1] {
			gradient[199 * m]
		}
	}
}
</source>
    <time>0.0</time>
    <translation>0.38698069985019584</translation>
    <translation>-0.06070612933203136</translation>
    <translation>1.3400956406250004</translation>
    <translation>0.0</translation>
</mandelbrot>
