<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<mandelbrot>
    <timestamp>2015-10-14 17:01:55</timestamp>
    <julia>true</julia>
    <point>-0.2607142857142857</point>
    <point>0.4357142857142857</point>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <source>fractal {
	orbit [-1.5 - 1.5i,+1.5 + 1.5i] [x,n,z] {
		trap circle0 [&lt;-0.1,-0.5&gt;] {
			MOVETO(&lt;1,0.5&gt;);
			LINETO(&lt;-1,0.5&gt;);
			ARCTO(&lt;-1.5,0.5&gt;,&lt;-1.5,0&gt;);
			ARCTO(&lt;-1.5,-0.5&gt;,&lt;-1,-0.5&gt;);
			LINETO(&lt;1,-0.5&gt;);
			ARCTO(&lt;1.5,-0.5&gt;,&lt;1.5,0&gt;);
			ARCTO(&lt;1.5,0.5&gt;,&lt;1,0.5&gt;);
		}
		begin {
			mmax = 0;
			z = 0;
		}
		loop [0, 200] (circle0 ? x) {
			x = x * x * x * x + w;
			m = mod(x);
			if (m &gt; mmax) {
				mmax = m;
				z = x;
			}
		}
	}
	color [(1,0,0,0)] {
		palette gradient0 {
			[#FF000000 &gt; #FFD30000, 20];
			[#FFD30000 &gt; #FFFFFF00, 110];
			[#FFFFFF00 &gt; #FFFFFFFF, 40];
			[#FFFFFFFF &gt; #FF030062, 30];
		}
		init {
			m = mod(z);
			q = 199 * (m - floor(m));
		}
		rule (n &gt; 1) [1] {
			gradient0[q]
		}
	}
}
</source>
    <time>0.0</time>
    <traslation>0.322021786511943</traslation>
    <traslation>-0.7033210036160367</traslation>
    <traslation>0.32557130579678173</traslation>
    <traslation>0.0</traslation>
</mandelbrot>
