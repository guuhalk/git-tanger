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


  ngOnInit(){
    this.formInput = this.formBuilder.group({
      baseBranch: new FormControl("",[Validators.required]),
      newBranch: new FormControl("",[Validators.required]),
      path: new FormControl("",[Validators.required]),
      user: new FormControl("",[Validators.required]),
      credentials : new FormControl("",[Validators.required]),
    });

  }

  constructor( private pageService: PageService ,private formBuilder: FormBuilder) {
    this.baseBranch = ""
    this.newBranch= ""
    this.path= ""
    this.user= ""
    this.credentials= ""
   }

  submit(){
    if (!this.formInput.valid) {
      console.log("Formulário inválido");
      return;
    }
    this.pageService.submit(this.newBranch,this.baseBranch ,this.path, this.user, this.credentials).subscribe((m)=>{

    },
    (error) => console.log(error)
    );
  }






}
