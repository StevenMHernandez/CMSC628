reset

set title ""
set nologscale x
#set xrange [105:200]
#set yrange [0:3]
#set xlabel "Time (s)" font "Times-New Roman,16"
#set ylabel "Throughput (b/s)" font "Times-New Roman,16"
set xtics 10
set datafile separator ","
set tics font "Times-New Roman, 12"

plot for [col=1:1] "data/mobilityModels/gaussmarkov" using 1:2:xtic(10) title 'GaussMarkov' with linespoints, \
 for [col=1:1] "data/mobilityModels/randomwaypoint" using 1:2:xtic(10) title 'RandomWaypoint' with linespoints
