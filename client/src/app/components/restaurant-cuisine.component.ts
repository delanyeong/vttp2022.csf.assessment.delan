import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-cuisine',
  templateUrl: './restaurant-cuisine.component.html',
  styleUrls: ['./restaurant-cuisine.component.css']
})
export class RestaurantCuisineComponent implements OnInit {
  
  // TODO Task 3
	// For View 2
  params$!: Subscription
  listOfResByCui: String [] = []

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private resSvc : RestaurantService) { }

  ngOnInit(): void {
    this.params$ = this.activatedRoute.params.subscribe(
      params => {
        const cuisine = params['cuisine']
        this.resSvc.getRestaurantsByCuisine(cuisine)
          .then(result => {
            this.listOfResByCui = result
            console.info('>>> comments: ', this.listOfResByCui)
          })
          .catch(error => {
            console.error('>> error: ', error)
          })
      }
    )
  }
}
