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

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'gallery', component: GalleryComponent }, //TODO GALLERY COMPONENT
  { path: 'login', component: LoginComponent },
  { path: 'eventSignup', component: EventSignupComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'search', component: SearchResultsComponent },
  { path: 'search/:param', component: SearchResultsComponent },
  { path: 'profile', component: UserComponent },
  { path: 'faq', component: FaqComponent },
  { path: 'eventDetails', component: EventDetailsComponent },
  { path: 'pastEvents', component: PastEventsComponent }, //TODO PAST EVENT COMPONENT
  {path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
