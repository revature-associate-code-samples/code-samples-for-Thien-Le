import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription, Observable } from 'rxjs';

import { GlobalsService } from '../globals.service';

@Component({
  selector: 'app-waiting-page',
  templateUrl: './waiting-page.component.html',
  styleUrls: ['./waiting-page.component.scss']
})
export class WaitingPageComponent implements OnInit, OnDestroy {


  public gameCategory;
  public gameNumberOfQuestion;
  public gameName;

  public disconnectOk = false;

  public userId;

  users: any;

  // Subscription status
  public subscribed = false;

  public startGame(): void {

    const self = this;

    $.ajax({
      url: self.globals.getApiUrl() + 'start-game-connect',
      method: 'POST',
      data: {
        key: self.globals.getLobbyKey()
      },
      crossDomain: true,
      xhrFields: { withCredentials: true },
      success: function (res) {
        self.unsubscribe();
        self.disconnectOk = true;
        self.router.navigate(['game']);
      },
    });
  }

  public disconnect(): void {

    const self = this;

    if (!this.disconnectOk) {
      $.ajax({
        url: self.globals.getApiUrl() + 'disconnect',
        method: 'GET',
        crossDomain: true,
        xhrFields: { withCredentials: true }
      });
    }
  }


  constructor(
    public router: Router,
    public globals: GlobalsService
  ) { }

  ngOnInit() {
    if(!this.globals.getLobbyKey()) {
      this.router.navigate(['']);
      return;
    } else {
      this.gameCategory = this.globals.getGameCategory();
      this.gameName = this.globals.getLobbyName();
      this.gameNumberOfQuestion = this.globals.getLobbyQuestions();
      this.userId = this.globals.getUserId();

      console.log(this.gameCategory);
      console.log(this.gameNumberOfQuestion);
      console.log(this.userId);
      this.connect();
    }
  }

  ngOnDestroy(): void {
    this.disconnect();
    this.unsubscribe();
  }

  public unsubscribe() {
    this.data_subscription = null;
    this.data_observable = null;
    this.chat_observable = null;
    this.chat_subscription = null;

    //this._stompService.disconnect();
    if(this.subscribed){
      this._stompService.deactivate();
      this.subscribed = false;
    }
    //this._stompService.
  }
}
