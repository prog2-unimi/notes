/*

Copyright 2021 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2.notes.tgerds;

import java.util.List;

public class Examples {

  List<? extends Number> upper;
  List<? super Number> lower;

  List<Number> n;
  List<Integer> i;
  List<Object> o;

  List<? extends Number> en;
  List<? extends Integer> ei;
  List<? extends Object> eo;

  List<? super Number> sn;
  List<? super Integer> si;
  List<? super Object> so;

  void upperbound() {

    // prima proprietà (T = Number)

    upper = n; // List<Number> is a subtype of List<? extends Number>

    // seconda proprietà (T = Number, S = Integer)

    upper = ei; // List<? extends Integer> is a subtype of List<? extends Number>

    // per transitività (T = Number, S = Integer)

    upper = i; // List<Integer> is a subtype of List<? extends Number>

    // per controllo, i due assegnamenti seguenti non compilano

    /*
    upper = o; // List<Object> isn't a subtype of List<? extends Number>
    upper = eo; // List<? extends Object> isn't a subtype of List<? extends Number>
    */
  }

  void lowerbound() {

    // prima proprietà (T = Number)

    lower = n; // List<Number> is a subtype of List<? super Number>

    // seconda proprietà (T = Object, S = Number)

    lower = so; // List<? super Object> is a subtype of List<? super Number>

    // per transitività (T = Object, S = Number)

    lower = o; // List<Object> is a subtype of List<? super Number>

    // per controllo, i due assegnamenti seguenti non compilano

    /*
    lower = i; // List<Integer> isn't a subtype of List<? super Number>
    lower = si; // List<? super Integer> isn't a subtype of List<? super Number>
    */
  }
}
