reset

set title ""
set nologscale x
set xrange [105:200]
#set yrange [0:3]
set xlabel "Time (s)" font "Times-New Roman,16"
set ylabel "Throughput (kb/s)" font "Times-New Roman,16"
set xtics 10
set datafile separator ","
set tics font "Times-New Roman, 12"

plot for [col=1:1] "data/optimalConfig/best.dsr-gaussmarkov" using 1:2:xtic(10) title 'Best-case DSR (RandomWaypoint, Speed=(0.0,0.0)' with linespoints, \
 for [col=1:1] "data/assignment1/dsr-gaussmarkov" using 1:2:xtic(10) title 'Standard DSR, RandomWaypoint, Speed=(0.0,20)' with linespoints, \
 for [col=1:1] "data/optimalConfig/bad.dsr-gaussmarkov" using 1:2:xtic(10) title 'Bad-case DSR (RandomWaypoint, Speed=(100.0,100.0)' with linespoints, \
 for [col=1:1] "data/optimalConfig/worst.dsr-gaussmarkov" using 1:2:xtic(10) title 'Worst-case DSR (RandomWaypoint, Speed=(1000.0,1000.0)' with linespoints
