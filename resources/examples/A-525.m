<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<mandelbrot>
    <timestamp>2015-08-15 15:13:42</timestamp>
    <julia>true</julia>
    <point>0.25049922028057114</point>
    <point>1.3856295219778688E-5</point>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <source>fractal {
	orbit [-3.0 - 1.5i,+0.0 + 1.5i] [x,n,m] {
		trap circle0 [&lt;0,0&gt;] {
			MOVETO(&lt;1,0&gt;);
			LINETO(&lt;0,1&gt;);
			LINETO(&lt;-1,0&gt;);
			LINETO(&lt;0,-1&gt;);
			LINETO(&lt;1,0&gt;);
		}
		trap circle1 [&lt;0,0&gt;] {
			MOVETO(&lt;1.1,0&gt;);
			CURVETO(&lt;1.1,1.1&gt;,&lt;0,1.1&gt;,&lt;0,1.1&gt;);
			CURVETO(&lt;-1.1,1.1&gt;,&lt;-1.1,0&gt;,&lt;-1.1,0&gt;);
			CURVETO(&lt;-1.1,-1.1&gt;,&lt;0,-1.1&gt;,&lt;0,-1.1&gt;);
			CURVETO(&lt;1.1,-1.1&gt;,&lt;1.1,0&gt;,&lt;1.1,0&gt;);
		}
		begin {
			dmax = 0;
			m = 0;
		}
		loop [0, 7000] (mod2(x) &gt; 40 | (circle0 ~? x &amp; circle1 ? x)) {
			x = x * x + w;
			dmax = max(dmax, mod2(x));
		}
		end {
			m = dmax - floor(dmax);
		}
	}
	color [(1,0,0,0)] {
		palette gradient {
			[#FFFF0A0A &gt; #FFFFFFFF, 20];
			[#FFFFFFFF &gt; #FF0042A9, 60];
			[#FF0042A9 &gt; #FF000000, 120];
		}
		rule (n &gt; 0) [1] {
			gradient[199 * re(m)]
		}
	}
}
</source>
    <time>0.0</time>
    <translation>0.46190182907789423</translation>
    <translation>-0.9016920244815421</translation>
    <translation>0.14204568230027756</translation>
    <translation>0.0</translation>
</mandelbrot>
