import { UserService } from './service/user.service';
import { PrivatePostService } from './service/private-post.service';
import { PrivateEventService } from './service/private-event.service';
import { PrivateCommentService } from './service/private-comment.service';
import { GiftService } from './service/gift.service';
import { EventService } from './service/event.service';
import { EventPostService } from './service/event-post.service';
import { EventCommentService } from './service/event-comment.service';
import { BudgetService } from './service/budget.service';
// import { Budget } from './models/budget';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './components/user/user.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { HomeComponent } from './components/home/home.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutComponent } from './components/about/about.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { SearchResultsComponent } from './components/search-results/search-results.component';
import { HttpClientModule } from '@angular/common/http';
import { AddressService } from './service/address.service';
import { PaymentService } from './service/payment.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FooterComponent } from './components/footer/footer.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import { PastEventsComponent } from './components/past-events/past-events.component';
import { FaqComponent } from './components/faq/faq.component';
import { EventDetailsComponent } from './components/event-details/event-details.component';
import { ActivePipe } from './pipes/active.pipe';
import { DisabledPipe } from './pipes/disabled.pipe';
import { UserEventsPipe } from './pipes/user-events.pipe';
import { UserPrvEventsPipe } from './pipes/user-prv-events.pipe';
import { FilterPipe } from './pipes/filter.pipe';
// import { PostComponent } from './components/post/post.component';
import { EventSignupComponent } from './components/event-signup/event-signup.component';
import { MessageBoardComponent } from './components/message-board/message-board.component';
import { AdminComponent } from './components/admin/admin.component';
import { BudgetFilterPipe } from './pipes/budget-filter.pipe';
import { PrivateEventComponent } from './components/private-event/private-event.component';
import { TermsOfServiceComponent } from './components/terms-of-service/terms-of-service.component';
import { UserInEventPipe } from './pipes/user-in-event.pipe';
// import { ComponentComponent } from './component/component.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    NavBarComponent,
    HomeComponent,
    ContactComponent,
    AboutComponent,
    RegisterComponent,
    LoginComponent,
    LogoutComponent,
    NotFoundComponent,
    SearchResultsComponent,
    FooterComponent,
    GalleryComponent,
    PastEventsComponent,
    FaqComponent,
    EventDetailsComponent,
    ActivePipe,
    DisabledPipe,
    UserEventsPipe,
    UserPrvEventsPipe,
    FilterPipe,
    EventSignupComponent,
    // PostComponent,
    MessageBoardComponent,
    AdminComponent,
    BudgetFilterPipe,
    PrivateEventComponent,
    TermsOfServiceComponent,
    UserInEventPipe,
    // ComponentComponent
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule, NgbModule, ReactiveFormsModule],
  providers: [
    AddressService,
    BudgetService,
    EventCommentService,
    EventPostService,
    EventService,
    GiftService,
    PaymentService,
    PrivateCommentService,
    PrivateEventService,
    PrivatePostService,
    UserService,
    UserEventsPipe,
    ActivePipe,
    DisabledPipe,
    UserPrvEventsPipe,
    UserInEventPipe
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
