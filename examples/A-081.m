<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<mandelbrot>
    <timestamp>2015-04-28 21:54:01</timestamp>
    <julia>true</julia>
    <point>0.425</point>
    <point>0.32</point>
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
		trap trap1 [&lt;0,0&gt;] {
			MOVETO(&lt;-1.0,+0&gt;);
			ARCTO(&lt;1,4&gt;);
			ARCTO(&lt;2,5&gt;);
			ARCTO(&lt;3,-6&gt;);
			ARCTO(&lt;-4,0&gt;);
		}		
		trap trap2 [&lt;0,0&gt;] {
			MOVETO(&lt;-3.0,+0.25&gt;);
			LINETO(&lt;-3.0,+0.25&gt;);
			LINETO(&lt;+3.0,+0.45&gt;);
			LINETO(&lt;+3.0,-0.45&gt;);
			LINETO(&lt;-3.0,-0.2&gt;);
		}		
		loop [0, 1000] (trap1 ~? x &amp; trap2 ~? x) {
			x = x * x + w;
		}
	}
	color [#FF000000] {
		palette gradient {
			[#FF000000 &gt; #90FFFFFF, 10];
			[#FFFF0000 &gt; #40FF0000, 10];
		}
		rule (n = 0) [0.5] {
			1,|x|,|x|,0
		}
		rule (n &gt; 0) [0.9] {
			gradient[n - 1]
		}
	}
}
</source>
    <time>0.0</time>
    <traslation>0.0</traslation>
    <traslation>0.0</traslation>
    <traslation>1.0</traslation>
    <traslation>0.0</traslation>
</mandelbrot>