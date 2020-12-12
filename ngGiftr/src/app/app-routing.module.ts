import { TermsOfServiceComponent } from './components/terms-of-service/terms-of-service.component';
import { AdminComponent } from './components/admin/admin.component';
import { MessageBoardComponent } from './components/message-board/message-board.component';
import { UserComponent } from './components/user/user.component';
import { SearchResultsComponent } from './components/search-results/search-results.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutComponent } from './components/about/about.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import { PastEventsComponent } from './components/past-events/past-events.component';
import { FaqComponent } from './components/faq/faq.component';
import { EventDetailsComponent } from './components/event-details/event-details.component';
import { EventSignupComponent } from './components/event-signup/event-signup.component';
import { PrivateEventComponent } from './components/private-event/private-event.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'gallery', component: GalleryComponent },
  { path: 'login', component: LoginComponent },
  { path: 'eventSignup', component: EventSignupComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'search', component: SearchResultsComponent },
  { path: 'search/:param', component: SearchResultsComponent },
  { path: 'profile', component: UserComponent },
  { path: 'terms', component: TermsOfServiceComponent },
  { path: 'faq', component: FaqComponent },
  { path: 'eventDetails', component: EventDetailsComponent },
  { path: 'pastEvents', component: PastEventsComponent },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'post/:id', component: MessageBoardComponent },
  { path: 'privateEvent', component: PrivateEventComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
