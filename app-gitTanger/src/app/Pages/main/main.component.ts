import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { PageService } from 'src/app/Service/page.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent{

  formInput!: FormGroup
  baseBranch:string
  newBranch:string
  path:string
  user:string
  credentials:string
  responseMessage:any = '';
  progressTime:boolean = false;


  ngOnInit(){
    this.formInput = this.formBuilder.group({
      baseBranch: new FormControl("",[Validators.required]),
      newBranch: new FormControl("",[Validators.required]),
      path: new FormControl("",[Validators.required]),
      user: new FormControl("",[Validators.required]),
      credentials : new FormControl("",[Validators.required]),
    });

  }

  constructor( private pageService: PageService ,private formBuilder: FormBuilder, private router: Router) {
    this.baseBranch = ""
    this.newBranch= ""
    this.path= ""
    this.user= ""
    this.credentials= ""

   }

   redirectCherry(){
    this.router.navigateByUrl("/cherry-pick")
   }

  submit(){

    this.progressTime = true;

    if (!this.formInput.valid) {
      console.log("Formulário inválido");
      return;
    }
    this.pageService.submit(this.newBranch,this.baseBranch ,this.path, this.user, this.credentials).subscribe((m)=>{



      setTimeout(() => {
        this.progressTime = false;
        this.responseMessage = m.body;
        console.log(m.body)
      }, 3000)



    },
    (error) => {
      setTimeout(() => {
        this.progressTime = false;
      }, 3000)
      console.log(error)
      console.log("to no erro")
    }
    );
  }






}
