% The beginning of the project
close all
clear all
clc

%coordinate system is set where launch pad is [0,0,0]
%   this can be redefined but it would take some work in recalculating the
%   intial positions defined below
    

%%Variable initializations

%rocket properties
velocity = [0, 0, 0];
position = [0, 0, 0];
acceleration = [0, 0, 0]; %pre-init to reduce chance of errors
thrust = [0,1e7,0]; %rocket thrust




%n-body system properties, initial
% xposition, yposition, zposition, mass, xvelocity, yvelocity, zvelocity
%any amount of bodies can be placed inside this system matrix, we chose two
%because its far easier to test for bugs
system = [...
        [0,-6.371e6,0],5.972e24, [0,0,0], 6.371e6;... %earth
        [0,383.4e6,0],7.3276e22, [-1.022e3,0,0], 1e5... %moon (just made up the radius lol)
    ];


%possible future additions (if someone else gets this project next year)
%airResistanceFactor = k
%angleOfAttack = theta
%liftFromAir = f(a,b,c)
%collisionboxes

% TODO add a wait for the game to begin otherwise the rocket will fall into earth at t=0    
%       or, add collisions :)

%General notes for the physics simulation
%startin position is set to be [0,0]
%all other calculations are based off this assumption
%so earth's starting position is [0,-6.371e6,0] (easier for n-body systems)


%%Pre-loop init
%figure 
%set(gcf, 'WindowState', 'fullscreen') %open a graph to fill whole screen

%%Lets get this GUI working :)
javaaddpath(pwd+"/gui") %add current working directory to java dynamic class paths
javaaddpath(pwd)
output = Window_Container(); %create the output window


%setup output

output.setVelocity(velocity);
output.setPosition(position);
output.setSystem(system);


%pause(1);



startTime = clock; %used in x-axis calculation for graphing
timeFromStart = 0; %pre-declare so while-loop doesn't mess up (do while)
t1=clock;%pre-declare for timeFromStart declaration below


%{
demonstration of how this all works :)

disp("Begin test!")
counter = 0;
while(counter < 10)
    
    counter = counter + 1;
    
    velocity = velocity + [1, 2*counter, 0];
    
    output.setVelocity(velocity);
    
    position = findPosition(velocity, position, .1);
    
    output.setPosition(position);
    
    output.update();
    
    pause(.1);
    
    disp(velocity)
    
end

output.kill()
pause(2);
clear output
 %}

%%this loop is broken on purpose for testing
while(timeFromStart <= 200)
    % TODO this can be optimized by combining linear approximations later
    %also, graphing can be optimized
    
    %%Loop counter variable
    timeFromStart = etime(t1, startTime);
    
    %%GRAPHING (commented out bc jfc its slow)
    %{
    
    %add graph titles
    subplot(3,6,1)
    title('P X');
    subplot(3,6,2)
        title('V X');
    subplot(3,6,3)
        title('A X');

    subplot(3,6,7)
        title('P Y');
    subplot(3,6,8)
        title('V Y');
    subplot(3,6,9)
        title('A Y');

    subplot(3,6,13)
        title('P Z');
    subplot(3,6,14)
        title('V Z');
    subplot(3,6,15)
        title('A Z');
    
        %if I remove any "hold on" the plots stop working!! I don't know
        %why
    subplot(3,6,1) %position x
    plot(timeFromStart, position(1), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,7) %position y
    plot(timeFromStart, position(2), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,13) %position z
    plot(timeFromStart, position(3), '.')
        xlim([0,100]);
        
    hold on
    subplot(3,6,2) %velocity x
    plot(timeFromStart, velocity(1), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,8) %velocity y
    plot(timeFromStart, velocity(2), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,14) %velocity z
    plot(timeFromStart, velocity(3), '.')
        xlim([0,100]);
    hold on
    
    hold on
    subplot(3,6,3) %acceleration x
    plot(timeFromStart, acceleration(1), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,9) %acceleration y
    plot(timeFromStart, acceleration(2), '.')
        xlim([0,100]);
    hold on
    subplot(3,6,15) %acceleration z
    plot(timeFromStart, acceleration(3), '.')
        xlim([0,100]);
    hold on
    
    
    % system plotting: moon
    subplot(3,6,4) %pos x
        plot(timeFromStart, system(2,1), '.')
        title("M P X")
        xlim([0,100])
    hold on
    subplot(3,6,10) %pos y
        plot(timeFromStart, system(2,2), '.')
        title("M P Y")
        xlim([0,100])
        hold on
    subplot(3,6,16) %pos z
        plot(timeFromStart, system(2,3), '.')
        title("M P Z")
        xlim([0,100])
        hold on
    
    % system plotting: moon
    subplot(3,6,5) %pos x
        plot(timeFromStart, system(2,5), '.')
        title("M V X")
        xlim([0,100])
        hold on
    subplot(3,6,11) %pos y
        plot(timeFromStart, system(2,6), '.')
        title("M V Y")
        xlim([0,100])
        hold on
    subplot(3,6,17) %pos z
        plot(timeFromStart, system(2,7), '.')
        title("M V Z")
        xlim([0,100])
        hold on
    
    
    drawnow
    %}
    
    %% ACTUAL PHYSICS
    %hefty calculations
    %update the gravity system
    system = updateSystem(system, finddt(t1)); 
    %update acceleration of rocket
    acceleration = findAcceleration(position,thrust,system);
    
    %update velocity of rocket
    velocity = findVelocity(acceleration, velocity, finddt(t1) );
    
    %detect collisions
    collision_flag = collision(position, velocity, system);
    if collision_flag == 1
        break;
    elseif collision_flag == 2
        velocity = [0,0,0];
    end
    %update position of rocket
    position = findPosition(velocity, position, finddt(t1));
    
    %% important to remove this and update to gui!
    thrust = [0,0,0];
    %temp line:
    disp(position)
    %+disp(velocity(2))
    
    % f(t) = f(t-dt) + dt*f'(t-dt) is linear approximation, valid for
    % sufficiently small dt. used in find functions
  
    
    %assists in 'dt', which is essential for accurate linear approximation
    %in variable loop speeds
    t1 = clock;

    
    
    
    %% Output updates
    output.setVelocity(velocity);
    output.setPosition(position);
    output.setSystem(system);
    output.update();
    
    %% Old Code
    %{
    old graphing stuff
    position = findPosition(velocity, position, finddt(t1));
    subplot(2,2,1)
    plot(c, position,'.');
%     xlim([0 10]);
%     ylim([0 0.1]);
    hold on
    subplot(2,2,2);
    plot(c, velocity,'.');
%     xlim([0 10]);
%     ylim([0 3]);
    hold on
    subplot(2,2,3);
    plot(c, acceleration, '.');
%     fprintf('%d is acceleration \n',acceleration);
%     fprintf('%d is velocity \n',velocity);
%     fprintf('%d is position \n', position);
    %assume time between t2 assignment and t1 assignment is negligble
    %break;
    drawnow;
    hold on
    %}
    
   
end
%% Clear variables
output.kill()
clear output
clear all
close all

disp("done!")

%% Function finddt finds time since last calculation
function dt = finddt(t1)
    dt = etime(clock,t1);
end

