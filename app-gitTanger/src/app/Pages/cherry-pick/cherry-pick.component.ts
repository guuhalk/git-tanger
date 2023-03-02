import { Router } from '@angular/router';
import { FormBuilder, FormControl, Validators, FormGroup } from '@angular/forms';
import { PageService } from 'src/app/Service/page.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-cherry-pick',
  templateUrl: './cherry-pick.component.html',
  styleUrls: ['./cherry-pick.component.scss']
})
export class CherryPickComponent {

  formInput!: FormGroup
  baseBranch:string
  newBranch:string
  path:string

  responseMessage:any = '';
  progressTime:boolean = false;


  ngOnInit(){
    this.formInput = this.formBuilder.group({
      baseBranch: new FormControl("",[Validators.required]),
      newBranch: new FormControl("",[Validators.required]),
      path: new FormControl("",[Validators.required])
    });
  }

  constructor( private pageService: PageService ,private formBuilder: FormBuilder, private router: Router) {
    this.baseBranch = ""
    this.newBranch= ""
    this.path= ""

   }

   redirectInicio(){
    this.router.navigateByUrl("/inicio")
   }

  submit(){

    this.progressTime = true;

    if (!this.formInput.valid) {
      console.log("Formulário inválido");
      return;
    }
    this.pageService.submitCherry(this.newBranch,this.baseBranch ,this.path).subscribe((m)=>{



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
