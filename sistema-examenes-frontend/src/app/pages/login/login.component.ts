import { Router } from '@angular/router';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData = {
    "username" : '',
    "password" : ''
  }

  constructor(private snack:MatSnackBar, private loginService:LoginService, private router:Router) { }

  ngOnInit(): void {
  }

  formSubmit(){
    // console.log("click en el boton de login");
    if(this.loginData.username.trim() == '' || this.loginData.username.trim() == null){
      this.snack.open("El nombre de usuario es requrido",'Aceptar',{
        duration: 3000
      })
      return;
    }

    if(this.loginData.password.trim() == '' || this.loginData.password.trim() == null){
      this.snack.open("La contraseña es requerida",'Aceptar',{
        duration: 3000
      })
      return;
    }

    this.loginService.genarateToken(this.loginData).subscribe(
      (data:any) => {
        console.log(data);
        this.loginService.loginUser(data.token)
        this.loginService.getCuerrentUser().subscribe((user:any) =>{
          this.loginService.setUser(user)
          console.log(user);

          if(this.loginService.getUserRol() == "ADMIN"){
            //Dashboard admin
            // window.location.href = '/admin'
            this.router.navigate(['admin']);
            this.loginService.loginStatusSubject.next(true);
          }else if(this.loginService.getUserRol() == "NORMAL"){
            // window.location.href = '/user-dasboard'
            this.router.navigate(['user-dashboard/0']);
            this.loginService.loginStatusSubject.next(true);
          }else{
            this.loginService.logout();
          }
        })
      },(error) => {
        console.log(error);
        this.snack.open("Detalles invalidos, vuelve a intentar",'Aceptar',{
          duration: 3000
        })
      }
    )

  }

}
