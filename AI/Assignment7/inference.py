# -*- coding: utf-8 -*-
"""
Created on Tue Apr 27 14:20:51 2021

@author: tudor
"""

import torch
import torch.nn.functional as F

import myModel



# we load the model

filepath = "myNet.pt"
ann = myModel.Net(2,10,1)
ann.load_state_dict(torch.load('myNetwork.pt'))
ann.eval()

# visualise the parameters for the ann (aka weights and biases)
for name, param in ann.named_parameters():
    if param.requires_grad:
        print (name, param.data)


x =float(input("x = "))
y =float(input("y= "))
x = torch.tensor([x, y])
print(ann(x).item())