/* 
 * File:   main.cpp
 * Author: kottofy
 *
 * Created on April 26, 2011, 12:35 PM
 *
 * A program that allocates non-sharable resourcse to processes using the
 * Deadlock avoidance algorithm that can have a maximum of 10 process and 10
 * resources.
 *
 */

#include <cstdlib>
#include <iostream>
#include <fstream>

using namespace std;

/*
 * 
 */
int main(int argc, char** argv)
{
    char fileName[20];
    int rrm[10][10];
    int adjacency[10][10];
    int path[10][10];
    int wait[10][1];
    int alloc[10][1];
    bool fileIsOpen = false;
    char c;

    do
    {
        cout << "\nInput the filename of the RRM: " << endl;
        cin >> fileName;

        //open file and save to char rrm[10][10]
        ifstream file(fileName);
        if (file.is_open())
        {
            fileIsOpen = true;
            cout << "\nResource Requirement Matrix\n" << endl;
            cout << "  0 1 2 3 4 5 6 7 8 9 " << endl;
            cout << "---------------------" << endl;
            for (int i = 0; i < 10; i++)
            {
                cout << i;
                for (int j = 0; j < 10; j++)
                {
                    file >> c;
                    rrm[i][j] = (int) c - 48;
                    cout << " " << rrm[i][j];
                }
                cout << endl;
            }
        }
        else
            cout << "Could not open file." << endl;
        //       cout << "DEBUG - fileIsOpen: " << fileIsOpen << endl;
    } while (!fileIsOpen);

    /*go ahead and set all values in the path and
    adjancency matrices to 0*/
    for (int i = 0; i < 10; i++)
    {
        for (int j = 0; j < 10; j++)
        {
            adjacency[i][j] = 0;
        }
    }
    for (int i = 0; i < 10; i++)
    {
        for (int j = 0; j < 10; j++)
        {
            path[i][j] = 0;
        }
    }
    for (int i = 0; i < 10; i++)
    {
        for (int j = 0; j < 10; j++)
        {
            wait[i][j] = -1;
        }
    }
    for (int i = 0; i < 10; i++)
    {
        alloc[i][0] == -1;
    }

    cout << "\nENTER 'Q' TO QUIT\n\n";

    // first things first - determine which resources 
    //are available at each insert


    int p;
    int r;

    while (1)
    {
        cout << "Please enter \"R\" and a process (0-9) and a resource (0-9): \n> ";
        cin >> c;

        if (c == 'q' || c == 'Q')
            break;
        else if (c == 'r' || c == 'R')
        {
            cout << "> ";
            cin >> p;
            cout << "> ";
            cin >> r;

            if ((p < 0 || p > 9) || (r < 0 || r > 9))
            {
                cout << "Incorrect Process Resource pair. Please try again." << endl;
                continue;
            }

            //check to see if process is able to request allocation of resource
            if (rrm[p][r] == 1) // if it equals 1
            {
                cout << "Process " << p << " is requesting allocation of resource "
                        << r << endl;

                if (alloc[r][0] == 1)
                {
                    cout << "Resource is already in use" << endl;
                    cout << "Trying to add to wait table..." << endl;

                    if (wait[r][0] > 0)
                        cout << "Unable to add to wait table. Resource is probably "
                            << "current being waited on" << endl;
                    else
                    {
                        cout << "Process " << p << " is waiting on resource " << r << endl;
                        wait[r][0] = p;

                        /******************************************/
                        cout << "\nWait Table: " << endl;
                        cout << "Resource  |  Process" << endl;
                        cout << "--------------------" << endl;
                        for (int i = 0; i < 10; i++)
                            if (wait[i][0] > -1)
                                cout << "    " << i << "     |     " << wait[i][0] << endl;
                        /********************************************/
                    }
                }
                else
                {
                    cout << "Resource is currently not in use. Attempting to allocate..." << endl;

                    //add to adjacency matrix using temporary values
                    //2 converts back to 1, 3 converts back to 0
                    for (int process = 0; process < 10; process++)
                    {
                        if (rrm[process][r] == 1) //go through every process at the resource
                        {
                            if (process != p)
                            {
                                if (adjacency[process][p] == 0)
                                {
                                    adjacency[process][p] = 2;
                                }
                                else if (adjacency[process][p] == 1)
                                {
                                    adjacency[process][p] = 3;
                                }
                                if (path[process][p] == 0)
                                {
                                    path[process][p] = 2;
                                }
                                else if (path[process][p] == 1)
                                {
                                    path[process][p] = 3;
                                }

                                //add to the path matrix
                                //row(i) = row(i) || row(j)
                                //col(j) = col(i) || col(j)
                                for (int i = 0; i < 10; i++)
                                {
                                    // p: R argument1
                                    // process: non-p match in RRM
                                    if (path[p][i] != 0 || path[process][i] != 0)
                                    {
                                        if (path[p][i] < 2)
                                            path[p][i] += 2;
                                    }

                                }
                                for (int i = 0; i < 10; i++)
                                {
                                    // p: R argument1
                                    // process: non-p match in RRM
                                    if (path[i][p] != 0 || path[i][process] != 0)
                                    {
                                        if (path[i][process] < 2)
                                            path[i][process] += 2;
                                    }
                                }

                                /* ORIGINAL KO SPECIAL TECHNIQUE ALSO CRAZY AND COMFUSING
                                for (int i = 0; i < 10; i++)
                                {
                                    int first = -1;
                                    if (path[p][i] != 0)
                                        first = 1;
                                    else
                                        first = 0;

                                    int second = -2;
                                    if (path[process][i] != 0)
                                        second = 1;
                                    else
                                        second = 0;

                                    int orA = -1;
                                    if (first || second == 1)
                                        orA = 1;
                                    else
                                        orA = 0;

                                    if (orA == 0)
                                        path[p][i] = 2;
                                    else
                                        path[p][i] = 3;
                                }
                                for (int j = 0; j < 10; j++)
                                {
                                    int first = -1;
                                    if (path[j][process] != 0)
                                        first = 1;
                                    else
                                        first = 0;

                                    int second = -2;
                                    if (path[j][p] != 0)
                                        second = 1;
                                    else
                                        second = 0;

                                    int orB = -1;
                                    if (first || second == 1)
                                        orB = 1;
                                    else
                                        orB = 0;

                                    if (orB == 0)
                                        path[j][p] = 2;
                                    else
                                        path[j][p] = 3;
                                } */
                            }
                        }
                    }


                    bool deadlock = false;
                    //use a boolean to determine if there is deadlock avoidance
                    //to rollback the changes.
                    for (int i = 0; i < 10; i++)
                    {
                        if (path[i][i] > 0)
                        {
                            deadlock = true;
                            break;
                        }
                    }

                    if (deadlock == true) //deadlock has occured
                    {
                        cout << "\nDeadlock Avoidance!\n";
                        for (int i = 0; i < 10; i++)
                        {
                            for (int j = 0; j < 10; j++)
                            {
                                if (adjacency[i][j] == 2)
                                {
                                    adjacency[i][j] = 0;
                                }
                                if (adjacency[i][j] == 3)
                                {
                                    adjacency[i][j] = 1;
                                }

                                if (path[i][j] == 2)
                                {
                                    path[i][j] = 0;
                                }

                                if (path[i][j] == 3)
                                {
                                    path[i][j] = 1;
                                }
                            }
                        }

                        //add to wait table if can
                        cout << "Trying to add to wait table..." << endl;

                        if (wait[r][0] > 0)
                        {
                            cout << "Unable to add to wait table. Resource is probably "
                                    << "currently being waited on" << endl;
                        }
                        else
                        {
                            cout << "Process " << p << " is waiting on resource " << r << endl;
                            wait[r][0] = p;

                            /******************************************/
                            cout << "\nWait Table: " << endl;
                            cout << "Resource  |  Process" << endl;
                            cout << "--------------------" << endl;
                            for (int i = 0; i < 10; i++)
                            {
                                if (wait[i][0] > -1)
                                {
                                    cout << "    " << i << "     |     " << wait[i][0] << endl;
                                }
                            }
                            /********************************************/
                        }
                    }
                    else //deadlock has not occured, commit.
                    {
                        for (int i = 0; i < 10; i++)
                        {
                            for (int j = 0; j < 10; j++)
                            {
                                if (adjacency[i][j] != 0)
                                {
                                    adjacency[i][j] = 1;
                                }

                                if (path[i][j] != 0)
                                {
                                    path[i][j] = 1;
                                }
                            }
                        }
                        alloc[r][0] = 1; //allocate resource
                    }

                    cout << "\nAdjacency Matrix\n";
                    cout << "  0 1 2 3 4 5 6 7 8 9 " << endl;
                    cout << "---------------------" << endl;
                    for (int i = 0; i < 10; i++)
                    {
                        cout << i;
                        for (int j = 0; j < 10; j++)
                        {
                            cout << " " << adjacency[i][j];
                        }
                        cout << endl;
                    }

                    cout << "\nPath Matrix\n";
                    cout << "  0 1 2 3 4 5 6 7 8 9 " << endl;
                    cout << "---------------------" << endl;
                    for (int i = 0; i < 10; i++)
                    {
                        cout << i;
                        for (int j = 0; j < 10; j++)
                        {
                            cout << " " << path[i][j];
                        }
                        cout << endl;
                    }
                }
            }
            else //process is unable to request allocation of resource
            {
                cout << "Process " << p << " cannot request allocation of resource "
                        << r << endl;
            }
        }
    }

    cout << "\nWait Table: " << endl;
    cout << "Resource  |  Process" << endl;
    cout << "--------------------" << endl;
    for (int i = 0; i < 10; i++)
        if (wait[i][0] > -1)
            cout << "    " << i << "     |     " << wait[i][0] << endl;

    cout << "Goodbye!\n";

    return 0;
}