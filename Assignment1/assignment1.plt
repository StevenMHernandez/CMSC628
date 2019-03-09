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

plot for [col=1:1] "data/assignment1/aodv-randomwaypoint" using 1:2:xtic(10) title 'AODV (RandomWaypoint)' with linespoints, \
 for [col=1:1] "data/assignment1/aodv-gaussmarkov" using 1:2:xtic(10) title 'AODV (GaussMarkov)' with linespoints, \
 for [col=1:1] "data/assignment1/dsr-randomwaypoint" using 1:2:xtic(10) title 'DSR (RandomWaypoint)' with linespoints, \
 for [col=1:1] "data/assignment1/dsr-gaussmarkov" using 1:2:xtic(10) title 'DSR (GaussMarkov)' with linespoints
