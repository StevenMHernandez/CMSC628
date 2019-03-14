clear all; close all;

for e = [0,1]
    % N,T_stop,Simulation,Theoretical
    X = load("data/part1." + string(e) + ".txt");

%     X = X(X(:,1) > 500, :);

    figure(e + 1);
    hold on;
    leg_strs = [];
    for u = unique(X(:,2))'
        X_u = X(X(:,2) == u,:);
        p = plot(X_u(:,1),X_u(:,3) / 10);
        p = plot(X_u(:,1),X_u(:,4), '--');
        leg_strs = [leg_strs "simulation (T_{stop}: " + string(u) + ")" "theoretical (T_{stop}: " + string(u) + ")"];
    end

    legend(leg_strs);
    xlabel("N");
    ylabel("Time (t)")
end