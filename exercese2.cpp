//Alkinoos-Michail Michalopoulos-Tsesmetzis
//icsd18135

#include <iostream>
//Exw valei Greeklish sxolia dioti sta ellinika buggarei.

using namespace std;

class Car  //Klash gia Amaksi
{
public:
    string model;
    string brandName;
    int year;
    Car();
    Car(string in_model, string in_brandName, int in_year);
};

class Moto  //Klash gia Mixani
{
public:
    string model;
    int cubism;
    float hph;
    Moto();
    Moto(string in_model, int in_cubism, float in_hph);
};

template <class T> //Template Klash gia to Garage
class Garage
{
private:
    T arr[10]; //Pinakas
public:
    int top;
    bool empt();
    bool full();
    bool add(T elem);
    bool rmv(T *elem);
    Garage();
    bool operator==(Garage<T> g){
        if (top==g.top){  // An oi piasmenes theseis twn garage einai ises.
            return true;
        }
        return false;
    }
};

template <typename T>
string comp(Garage<T> garage1,Garage<T> garage2);

int main() //Main sunarthsh tou programmatos mou.
{
    Car car1("X5", "BMW", 2015);
    Car car2("Fortwo", "SMART", 2009);
    Car car3("E45", "BMW", 2016);
    Moto moto1("DUKE", 990, 120);
    Moto moto2("GSXR", 1000, 180);
    Moto moto3("DRZ", 400, 65);
    Garage<Moto> garage3;
    Garage<Car> garage;
    Garage<Car> garage2;
    garage2.add(car2);
    garage.add(car3);
    garage.add(car2);
    garage.add(car2);
    garage3.add(moto1);
    garage3.add(moto2);
    garage3.add(moto3);
    cout<<"Size of garage3 : "<<garage3.top<<endl;
    garage3.rmv(&moto3);
    cout<<"Size of garage : "<<garage.top<<endl;
    cout<<"Size of garage2 : "<<garage2.top<<endl;
    cout<<"Size of garage3 : "<<garage3.top<<endl;
    cout<<comp(garage,garage2);
}


Car::Car(){} //Default constructor car

Car::Car(string in_model, string in_brandName, int in_year){ //Constructor Car
    in_model = model;
    in_brandName = brandName;
    in_year = year;
}

Moto::Moto(){} //Default constructor moto

Moto::Moto(string in_model, int in_cubism, float in_hph){ //Constructor moto.
    in_model = model;
    in_cubism = cubism;
    in_hph = hph;
}

template <typename T>
string comp(Garage<T> garage1,Garage<T> garage2){ //Sunarthsh gia thn sugkrish duo antikeimenwn Garage.
    if(garage1==garage2){ //Sugkrish twn duo antikeimenwn ths klashs Garage.
        return "The remaing spots are equals\n";
    }
    else
        return "The remaing spots are not equals\n";
}

template <typename T>
Garage<T>::Garage(){//Constructor Garage.
    top = 0;
}

template <typename T>
bool Garage<T>::empt(){//Sunarthsh pou elegxei an einai adeia to Garage.
    return top == -1;
}

template <typename T>
bool Garage<T>::full(){//Sunarthsh pou elegxei an einai gemato to Garage.
    return top == 9;
}

template <typename T>
bool Garage<T>::add(T elem){//Sunarthsh prosthikhs antikeimenou sthn stoiva tou Garage.
    if(full())
        return false;
    top++; //Afksish twn piasmenwn thesewn.
    arr[top] = elem;
    return true;
}

template <typename T>
bool Garage<T>::rmv(T *elem){//Sunarthsh afaireshs antikeimenou apo thn stoiva tou Garage.
    if(empt())
        return false;
    *elem = arr[top];
    top--; //Meiwsh twn piasmenwn thesewn.
    return true;
}
