clear all; close all;

% L,Upper,Average,Lower
X = load("data/part2.0.txt");

figure(1);
hold on;
leg_strs = [];
p = plot(X(:,1),X(:,2), '.-');
p = plot(X(:,1),X(:,3));
p = plot(X(:,1),X(:,4), '--');
leg_strs = [
"upper bound"
"simulation" 
"lower bound"
];

legend(leg_strs);
xlabel("L");
ylabel("Time (t)")